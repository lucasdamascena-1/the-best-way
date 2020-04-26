package br.com.fiap.thebestway.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fiap.thebestway.domain.Carro;
import br.com.fiap.thebestway.services.CarroService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/carros")
public class CarroResource {

	@Autowired
	private CarroService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Carro obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<Void> insert(@RequestBody Carro obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}
}
