package br.com.fiap.thebestway.domain.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.thebestway.domain.Carro;
import br.com.fiap.thebestway.dto.CarroNewDTO;
import br.com.fiap.thebestway.repositories.CarroRepository;
import br.com.fiap.thebestway.resources.exception.FieldMessage;

public class CarroInsertValidator implements ConstraintValidator<CarroInsert, CarroNewDTO> {

	@Autowired
	private CarroRepository repository;

	@Override
	public void initialize(CarroInsert ann) {
	}

	@Override
	public boolean isValid(CarroNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		Carro aux = repository.findByPlaca(objDto.getPlaca());

		if (aux != null) {
			list.add(new FieldMessage("Placa", "Placa já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}