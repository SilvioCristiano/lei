package com.silvio.cursomc.service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.silvio.cursomc.domain.Cliente;
import com.silvio.cursomc.domain.Pedido;


@Service
public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}