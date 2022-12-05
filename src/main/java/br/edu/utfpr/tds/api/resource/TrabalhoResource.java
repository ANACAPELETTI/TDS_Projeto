package br.edu.utfpr.tds.api.resource;

import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import br.edu.utfpr.tds.api.model.Trabalho;
import br.edu.utfpr.tds.api.repository.TrabalhoRepository;
import br.edu.utfpr.tds.api.repository.filter.TrabalhoFilter;
import br.edu.utfpr.tds.api.service.TrabalhoService;

@RestController
@RequestMapping("/trabalho")
public class TrabalhoResource {
	@Autowired //Spring vai gerenciar a interface
	private TrabalhoRepository trabalhoRepository;
	@Autowired //Spring vai gerenciar a interface
	private ApplicationEventPublisher publisher;
	@Autowired //Spring vai gerenciar a interface
	private TrabalhoService trabalhoService;
	@GetMapping //Quando for uma requisição do tipo Get no /cargo, irá cair nessa função
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TRABALHO') and #oauth2.hasScope('read')")
	public Page<Trabalho> pesquisar(TrabalhoFilter trabalhoFilter, Pageable pageable) {
	    return trabalhoRepository.filtrar(trabalhoFilter, pageable);
	}
	
	@PostMapping
	//@PreAuthorize("hasAuthority('ROLE_CADASTRAR_TRABALHO') and #oauth2.hasScope('write')")
	public ResponseEntity<Trabalho> criar(@RequestBody Trabalho trabalho, HttpServletResponse response) {
		Trabalho trabalhoSalvo = this.trabalhoRepository.save(trabalho);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, trabalhoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(trabalhoSalvo);
	}
	   
	@GetMapping("/{codigo}")
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TRABALHO') and #oauth2.hasScope('read')")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
	   Optional<Trabalho> pessoa = this.trabalhoRepository.findById(codigo);
	   return pessoa.isPresent() ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	//@PreAuthorize("hasAuthority('ROLE_REMOVE_TRABALHO') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT) //Código 204, ou seja, deu certo mas não tem nada para retornar
	public void remover(@PathVariable Long codigo) {
		this.trabalhoRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	//@PreAuthorize("hasAuthority('ROLE_EDITA_TRABALHO') and #oauth2.hasScope('write')")
	public ResponseEntity<Trabalho> atualizar(@PathVariable Long codigo, @Valid @RequestBody Trabalho trabalho) {
		Trabalho trabalhoSalvo = trabalhoService.atualizar(codigo, trabalho);
		return ResponseEntity.ok(trabalhoSalvo);
	}
}
