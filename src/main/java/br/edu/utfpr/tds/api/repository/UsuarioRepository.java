package br.edu.utfpr.tds.api.repository;

import java.util.Optional;
//import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.utfpr.tds.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	public Optional<Usuario> findByRa(String ra);
	//public List<Usuario> findByPermissoesDescricao(String permissaoDescricao);
}