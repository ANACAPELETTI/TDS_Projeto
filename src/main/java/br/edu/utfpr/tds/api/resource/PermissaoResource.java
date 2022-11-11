package br.edu.utfpr.tds.api.resource;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import br.edu.utfpr.tds.api.model.Permissao;
import br.edu.utfpr.tds.api.repository.PermissaoRepository;
import br.edu.utfpr.tds.api.service.PermissaoService;

@RestController
@RequestMapping("/permissoes")
public class PermissaoResource {
	@Autowired //Spring vai gerenciar a interface
	private PermissaoRepository permissaoRepository;
	@Autowired //Spring vai gerenciar a interface
	private ApplicationEventPublisher publisher;
	@Autowired //Spring vai gerenciar a interface
	private PermissaoService permissaoService;
	@GetMapping //Quando for uma requisição do tipo Get no /cargo, irá cair nessa função
	public List<Permissao> listar() {
	    return permissaoRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Permissao> criar(@Valid @RequestBody Permissao permissao, HttpServletResponse response) {
		Permissao permissaoSalva = this.permissaoRepository.save(permissao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, permissaoSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(permissaoSalva);
	}
	   
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
	   Optional<Permissao> permissao = this.permissaoRepository.findById(codigo);
	   return permissao.isPresent() ? ResponseEntity.ok(permissao) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT) //Código 204, ou seja, deu certo mas não tem nada para retornar
	public void remover(@PathVariable Long codigo) {
		this.permissaoRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Permissao> atualizar(@PathVariable Long codigo, @Valid @RequestBody Permissao permissao) {
		Permissao permissaoSalva = permissaoService.atualizar(codigo, permissao);
		return ResponseEntity.ok(permissaoSalva);
	}
}
