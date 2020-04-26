package br.com.fiap.thebestway.resources;

import br.com.fiap.thebestway.domain.Carro;
import br.com.fiap.thebestway.services.CarroService;
import javassist.tools.rmi.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
