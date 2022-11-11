package br.edu.utfpr.tds.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import br.edu.utfpr.tds.api.model.Trabalho;
import br.edu.utfpr.tds.api.repository.TrabalhoRepository;

@Service
public class TrabalhoService {
	@Autowired
	private TrabalhoRepository trabalhoRepository;
	public Trabalho atualizar (@PathVariable Long codigo, @RequestBody Trabalho trabalho) {
		Trabalho trabalhoSalvo = this.trabalhoRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(trabalho, trabalhoSalvo, "codigo");
		return this.trabalhoRepository.save(trabalhoSalvo);
	}
}
