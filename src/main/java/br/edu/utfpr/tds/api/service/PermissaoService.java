package br.edu.utfpr.tds.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import br.edu.utfpr.tds.api.model.Permissao;
import br.edu.utfpr.tds.api.repository.PermissaoRepository;

@Service
public class PermissaoService {
	@Autowired
	private PermissaoRepository permissaoRepository;
	public Permissao atualizar (@PathVariable Long codigo, @RequestBody Permissao permissao) {
		Permissao permissaoSalva = this.permissaoRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(permissao, permissaoSalva, "codigo");
		return this.permissaoRepository.save(permissaoSalva);
	}
}