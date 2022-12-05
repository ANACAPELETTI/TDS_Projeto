package br.edu.utfpr.tds.api.service;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import br.edu.utfpr.tds.api.model.Pessoa;
import br.edu.utfpr.tds.api.model.Telefone;
import br.edu.utfpr.tds.api.repository.PessoaRepository;
import br.edu.utfpr.tds.api.repository.TelefoneRepository;

@Service
public class PessoaService {
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private TelefoneRepository telefoneRepository;
	public Pessoa atualizar (@PathVariable Long codigo, @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = this.pessoaRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		
		//Telefone telefone = pessoaSalva.getCodigo();
		//telefone.setPessoa(pessoaSalva);
		return this.pessoaRepository.save(pessoaSalva);
	}
	
	public void atualizaPropriedade(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}
	
	private Pessoa buscarPessoaPeloCodigo(Long codigo) {
		Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
		if(pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva.get();
	}
}
