package com.patrickcarmo.meucarrinho.services;

import org.springframework.mail.SimpleMailMessage;

import com.patrickcarmo.meucarrinho.domain.Cliente;
import com.patrickcarmo.meucarrinho.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

	void sendNewPassWordEmail(Cliente cliente, String newPassword);
	
}