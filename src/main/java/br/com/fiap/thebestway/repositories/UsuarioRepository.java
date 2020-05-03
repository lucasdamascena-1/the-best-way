package br.com.fiap.thebestway.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.thebestway.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Transactional(readOnly = true)
	Usuario findByEmail(String email);

	@Transactional(readOnly = true)
	Usuario findByCpf(String cpf);

	@Transactional(readOnly = true)
	Usuario findByTelefone(String telefone);
}
