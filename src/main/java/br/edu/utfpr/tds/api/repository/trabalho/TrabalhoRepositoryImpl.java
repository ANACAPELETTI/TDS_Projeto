package br.edu.utfpr.tds.api.repository.trabalho;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Predicate;
import br.edu.utfpr.tds.api.model.Trabalho;
import br.edu.utfpr.tds.api.repository.filter.TrabalhoFilter;

public class TrabalhoRepositoryImpl implements TrabalhoRepositoryQuery{
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Trabalho> filtrar(TrabalhoFilter trabalhoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Trabalho> criteria = builder.createQuery(Trabalho.class);
		Root<Trabalho> root = criteria.from(Trabalho.class);
		Predicate[] predicates = criarRestricoes(trabalhoFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Trabalho> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(trabalhoFilter));
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(TrabalhoFilter trabalhoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Trabalho> root = criteria.from(Trabalho.class);
		Predicate[] predicates = criarRestricoes(trabalhoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
	
	private Predicate[] criarRestricoes(TrabalhoFilter trabalhoFilter, CriteriaBuilder builder, Root<Trabalho> root) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(trabalhoFilter.getTitulo())) {
			predicates.add(builder.like(builder.lower(root.get("titulo")), "%" + trabalhoFilter.getTitulo().toLowerCase() + "%")); 
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
