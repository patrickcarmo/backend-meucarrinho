package com.patrickcarmo.meucarrinho.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class EmailDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatorio")
	@Email(message="E-mail inválido")
	public String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public EmailDTO() {}
	
}
