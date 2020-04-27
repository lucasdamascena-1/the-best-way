package br.com.fiap.thebestway.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.fiap.thebestway.domain.Carro;
import br.com.fiap.thebestway.dto.CarroDTO;
import br.com.fiap.thebestway.repositories.CarroRepository;
import br.com.fiap.thebestway.services.exception.DataIntegrityException;
import br.com.fiap.thebestway.services.exception.ObjectNotFoundException;

@Service
public class CarroService {

	@Autowired
	private CarroRepository repository;

	public List<Carro> findAll() {
		return repository.findAll();
	}

	public Carro find(Integer id) throws ObjectNotFoundException {
		Optional<Carro> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}

	public Carro insert(Carro obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Carro update(Carro obj) {
		find(obj.getId());
		return repository.save(obj);
	}

	public void deleteById(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um relacionamento que tem dependências.");
		}
	}

	public Carro fromCarroDTO(CarroDTO objDTO, boolean isInsertMethod) {

		if (isInsertMethod) {
			return new Carro(null, objDTO.getMarca(), objDTO.getModelo(), objDTO.getPlaca(), 0.0, 0, 0);
		}

		return new Carro(objDTO.getId(), objDTO.getMarca(), objDTO.getModelo(), objDTO.getPlaca(),
				objDTO.getNotaMediaDeViagem(), objDTO.getQuantidadeDeCorridas(), objDTO.getDisponibilidade());
	}
}