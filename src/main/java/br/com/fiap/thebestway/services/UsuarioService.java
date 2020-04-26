package br.com.fiap.thebestway.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.thebestway.domain.Usuario;
import br.com.fiap.thebestway.repositories.UsuarioRepository;
import br.com.fiap.thebestway.services.exception.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Usuario find(Integer id) throws ObjectNotFoundException {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}
}
