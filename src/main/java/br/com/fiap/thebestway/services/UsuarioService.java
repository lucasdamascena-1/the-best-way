package br.com.fiap.thebestway.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.fiap.thebestway.domain.Usuario;
import br.com.fiap.thebestway.dto.UsuarioDTO;
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

	public Usuario fromUsuarioDTO(UsuarioDTO objDTO, boolean isInsertMethod) {

		if (isInsertMethod) {

			if (objDTO.getNome() == null || objDTO.getEmail() == null || objDTO.getCpf()  == null
					|| objDTO.getSenha() == null || objDTO.getTelefone() == null) {
				throw new NullPointerException("Valores inicializados com NULL.");
			}

			return new Usuario(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpf(), objDTO.getSenha(), objDTO.getTelefone(), 1);

		} else {
			if (objDTO.getEmail() == null || objDTO.getSenha() == null || objDTO.getStatus() == null) {
				throw new NullPointerException("Valores alterados para NULL.");
			}
		}

		return new Usuario(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), objDTO.getCpf(), objDTO.getSenha(), objDTO.getTelefone(),
				objDTO.getStatus());
	}

	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setSenha(obj.getSenha());
		newObj.setStatus(obj.getStatus());
	}
}