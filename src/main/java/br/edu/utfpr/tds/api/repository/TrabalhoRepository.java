package br.edu.utfpr.tds.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.tds.api.model.Trabalho;
import br.edu.utfpr.tds.api.repository.trabalho.TrabalhoRepositoryQuery;

public interface TrabalhoRepository extends JpaRepository<Trabalho, Long>, TrabalhoRepositoryQuery {

}
