package com.silvio.cursomc.service;


import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.silvio.cursomc.domain.Cliente;
import com.silvio.cursomc.domain.Pedido;



public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);
}