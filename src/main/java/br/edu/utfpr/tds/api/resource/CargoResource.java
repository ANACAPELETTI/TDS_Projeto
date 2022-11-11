package br.edu.utfpr.tds.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional; 
import br.edu.utfpr.tds.api.model.Cargo;
import br.edu.utfpr.tds.api.repository.CargoRepository;
 
@RestController
@RequestMapping("/cargo")
public class CargoResource {
   @Autowired //Spring vai gerenciar a interface
   private CargoRepository cargoRepository;
   //@CrossOrigin(origins = { "http://127.0.0.1:8000" }) //Permite que aplicações de outras portas acessem os recursos da aplicação
   @GetMapping //Quando for uma requisição do tipo Get no /cargo, irá cair nessa função
   public List<Cargo> listar() {
       return cargoRepository.findAll();
   }
   
   @GetMapping("/{codigo}")
   public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
       Optional<Cargo> cargo = this.cargoRepository.findById(codigo);
       return cargo.isPresent() ? ResponseEntity.ok(cargo) : ResponseEntity.notFound().build();
   }
}