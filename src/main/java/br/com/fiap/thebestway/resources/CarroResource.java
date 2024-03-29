package br.com.fiap.thebestway.resources;

//import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.thebestway.domain.Carro;
import br.com.fiap.thebestway.dto.CarroNewDTO;
import br.com.fiap.thebestway.dto.CarroUpdateDTO;
import br.com.fiap.thebestway.services.CarroService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/carros")
public class CarroResource {

	@Autowired
	private CarroService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Carro>> findAll() {
		List<Carro> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Carro> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Carro obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CarroNewDTO objDTO) {
		Carro obj = service.fromCarroDTO(objDTO);
		obj = service.insert(obj);		

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CarroUpdateDTO objDTO, @PathVariable Integer id) {
		Carro obj = service.fromCarroDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
