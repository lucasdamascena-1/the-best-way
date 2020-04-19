package br.com.fiap.thebestway.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.thebestway.domain.Carro;
import br.com.fiap.thebestway.repositories.CarroRepository;
import br.com.fiap.thebestway.services.exception.ObjectNotFoundException;

@Service
public class CarroService {

	@Autowired
	private CarroRepository repository;

	public Carro find(Integer id) throws ObjectNotFoundException {
		Optional<Carro> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado!"));
	}
}