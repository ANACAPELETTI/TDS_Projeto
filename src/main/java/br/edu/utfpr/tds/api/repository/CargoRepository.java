package br.edu.utfpr.tds.api.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.utfpr.tds.api.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{
 
}