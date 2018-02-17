package com.patrickcarmo.meucarrinho.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.patrickcarmo.meucarrinho.domain.Cliente;
import com.patrickcarmo.meucarrinho.domain.enums.TipoCliente;
import com.patrickcarmo.meucarrinho.dto.ClienteNewDTO;
import com.patrickcarmo.meucarrinho.repositories.ClienteRepository;
import com.patrickcarmo.meucarrinho.resources.exception.FieldMessage;
import com.patrickcarmo.meucarrinho.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente clienteEmail = repo.findByEmail(objDto.getEmail());
		if(clienteEmail != null) {
			list.add(new FieldMessage("email", "E-mail já existente"));
		}
		
		Cliente clienteCpf = repo.findByCpfOuCnpj(objDto.getCpfOuCnpj());
		if(clienteCpf != null) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF/CNPJ já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
