package br.edu.utfpr.tds.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.utfpr.tds.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
