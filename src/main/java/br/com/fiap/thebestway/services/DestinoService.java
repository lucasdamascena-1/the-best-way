package br.com.fiap.thebestway.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.thebestway.domain.Destino;
import br.com.fiap.thebestway.repositories.DestinoRepository;
import br.com.fiap.thebestway.services.exception.ObjectNotFoundException;

@Service
public class DestinoService {

	@Autowired
	private DestinoRepository repository;

	public List<Destino> findAll() {
		return repository.findAll();
	}

	public Destino find(Integer id) throws ObjectNotFoundException {
		Optional<Destino> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}
}
