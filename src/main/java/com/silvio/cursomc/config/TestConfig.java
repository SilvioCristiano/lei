package com.silvio.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.silvio.cursomc.service.DBService;
import com.silvio.cursomc.service.EmailService;
import com.silvio.cursomc.service.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.InstantiateTestDatabase();
		
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}