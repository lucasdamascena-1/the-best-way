package br.com.fiap.thebestway.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.thebestway.domain.Carro;
import br.com.fiap.thebestway.repositories.CarroRepository;

@Service
public class CarroService {

	@Autowired
	private CarroRepository repository;

	public Carro find(Integer id) {
		Optional<Carro> obj = repository.findById(id);
		return obj.orElse(null);
	}
}