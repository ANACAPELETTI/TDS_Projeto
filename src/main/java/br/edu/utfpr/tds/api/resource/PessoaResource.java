package br.edu.utfpr.tds.api.resource;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.edu.utfpr.tds.api.event.RecursoCriadoEvent;
import br.edu.utfpr.tds.api.model.Pessoa;
import br.edu.utfpr.tds.api.model.Telefone;
import br.edu.utfpr.tds.api.repository.PessoaRepository;
import br.edu.utfpr.tds.api.repository.TelefoneRepository;
import br.edu.utfpr.tds.api.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
	@Autowired //Spring vai gerenciar a interface
	private PessoaRepository pessoaRepository;
	@Autowired //Spring vai gerenciar a interface
	private ApplicationEventPublisher publisher;
	@Autowired //Spring vai gerenciar a interface
	private PessoaService pessoaService;
	@Autowired //Spring vai gerenciar a interface
	private TelefoneRepository telefoneRepository;
	@GetMapping //Quando for uma requisição do tipo Get no /cargo, irá cair nessa função
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
	public List<Pessoa> listar() {
	    return pessoaRepository.findAll();
	}
	
	@PostMapping
	//@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = this.pessoaRepository.save(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	   
	@GetMapping("/{codigo}")
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
	   Optional<Pessoa> pessoa = this.pessoaRepository.findById(codigo);
	   return pessoa.isPresent() ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT) //Código 204, ou seja, deu certo mas não tem nada para retornar
	//@PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		this.pessoaRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	//@PreAuthorize("hasAuthority('ROLE_EDITA_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
		return ResponseEntity.ok(pessoaSalva);
	}
	
	@PutMapping("/{codigo}/status")
	//@PreAuthorize("hasAuthority('ROLE_EDITA_PESSOA') and #oauth2.hasScope('read')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizaPropriedadeAtivo(@PathVariable Long codigo, @Valid @RequestBody Boolean ativo) {
		pessoaService.atualizaPropriedade(codigo, ativo);
	}
}
