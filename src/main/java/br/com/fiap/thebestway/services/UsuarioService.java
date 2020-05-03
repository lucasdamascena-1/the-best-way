package br.com.fiap.thebestway.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.fiap.thebestway.domain.Usuario;
import br.com.fiap.thebestway.dto.UsuarioNewDTO;
import br.com.fiap.thebestway.dto.UsuarioUpdateDTO;
import br.com.fiap.thebestway.repositories.UsuarioRepository;
import br.com.fiap.thebestway.services.exception.DataIntegrityException;
import br.com.fiap.thebestway.services.exception.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public List<Usuario> findAll() {
		return repository.findAll();
	}

	public Usuario find(Integer id) throws ObjectNotFoundException {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}

	public Usuario insert(Usuario obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void deleteById(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um relacionamento que tem dependências.");
		}
	}

	public Usuario fromUsuarioDTO(UsuarioNewDTO objDTO) {
		return new Usuario(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpf(), objDTO.getSenha(),
				objDTO.getTelefone(), 1);
	}

	public Usuario fromUsuarioDTO(UsuarioUpdateDTO objDTO) {
		return new Usuario(null, null, null, null, objDTO.getSenha(), objDTO.getTelefone(), objDTO.getStatus());
	}

	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setSenha(obj.getSenha());
		newObj.setTelefone(obj.getTelefone());
		newObj.setStatus(obj.getStatus());
	}
}