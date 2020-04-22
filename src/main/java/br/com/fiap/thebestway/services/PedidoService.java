package br.com.fiap.thebestway.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.thebestway.domain.Pedido;
import br.com.fiap.thebestway.repositories.PedidoRepository;
import br.com.fiap.thebestway.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido find(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado!"));
	}
}