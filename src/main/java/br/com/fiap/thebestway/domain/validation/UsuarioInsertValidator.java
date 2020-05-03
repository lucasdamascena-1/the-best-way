package br.com.fiap.thebestway.domain.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.thebestway.domain.Usuario;
import br.com.fiap.thebestway.domain.validation.utils.DocumentUtil;
import br.com.fiap.thebestway.dto.UsuarioNewDTO;
import br.com.fiap.thebestway.repositories.UsuarioRepository;
import br.com.fiap.thebestway.resources.exception.FieldMessage;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDTO> {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public void initialize(UsuarioInsert ann) {
	}

	@Override
	public boolean isValid(UsuarioNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		Usuario auxEmail = repository.findByEmail(objDTO.getEmail());
		Usuario auxCpf = repository.findByCpf(objDTO.getCpf());
		Usuario auxTelefone = repository.findByTelefone(objDTO.getTelefone());
		
		if (!DocumentUtil.isValidCPF(objDTO.getCpf())) {
			list.add(new FieldMessage("Cpf", "CPF inválido"));
		}

		if (auxEmail != null) {
			list.add(new FieldMessage("Email", "Email já existente"));
		}

		if (auxCpf != null) {
			list.add(new FieldMessage("Cpf", "Cpf já existente"));
		}

		if (auxTelefone != null) {
			list.add(new FieldMessage("Telefone", "Telefone já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}