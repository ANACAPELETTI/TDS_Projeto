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
import br.edu.utfpr.tds.api.model.Usuario;
import br.edu.utfpr.tds.api.repository.UsuarioRepository;
import br.edu.utfpr.tds.api.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {
	@Autowired //Spring vai gerenciar a interface
	private UsuarioRepository usuarioRepository;
	@Autowired //Spring vai gerenciar a interface
	private ApplicationEventPublisher publisher;
	@Autowired //Spring vai gerenciar a interface
	private UsuarioService usuarioService;
	@GetMapping //Quando for uma requisição do tipo Get no /cargo, irá cair nessa função
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public List<Usuario> listar() {
	    return usuarioRepository.findAll();
	}
	
	@PostMapping
	//@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
		Usuario usuarioSalvo = this.usuarioRepository.save(usuario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}
	   
	@GetMapping("/{codigo}")
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
	   Optional<Usuario> usuario = this.usuarioRepository.findById(codigo);
	   return usuario.isPresent() ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT) //Código 204, ou seja, deu certo mas não tem nada para retornar
	//@PreAuthorize("hasAuthority('ROLE_REMOVER_USUARIO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		this.usuarioRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	//@PreAuthorize("hasAuthority('ROLE_REDEFINE_SENHA')  and #oauth2.hasScope('read')")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long codigo, @Valid @RequestBody Usuario usuario) {
		Usuario usuarioSalvo = usuarioService.atualizar(codigo, usuario);
		return ResponseEntity.ok(usuarioSalvo);
	}
}
