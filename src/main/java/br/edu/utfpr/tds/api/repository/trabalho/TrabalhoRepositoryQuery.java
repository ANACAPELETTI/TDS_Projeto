package br.edu.utfpr.tds.api.repository.trabalho;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.edu.utfpr.tds.api.model.Trabalho;
import br.edu.utfpr.tds.api.repository.filter.TrabalhoFilter;

public interface TrabalhoRepositoryQuery {
	public Page<Trabalho> filtrar(TrabalhoFilter trabalhoFilter, Pageable pageable);
}
