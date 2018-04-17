package com.lei.backend.service;


import org.springframework.mail.SimpleMailMessage;

import com.lei.backend.domain.Cliente;
import com.lei.backend.domain.Pedido;



public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}