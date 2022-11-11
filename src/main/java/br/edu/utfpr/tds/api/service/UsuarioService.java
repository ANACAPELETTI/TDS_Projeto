package br.edu.utfpr.tds.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import br.edu.utfpr.tds.api.model.Usuario;
import br.edu.utfpr.tds.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	public Usuario atualizar (@PathVariable Long codigo, @RequestBody Usuario usuario) {
		Usuario usuarioSalvo = this.usuarioRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo");
		return this.usuarioRepository.save(usuarioSalvo);
	}
}
