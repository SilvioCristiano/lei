package com.silvio.cursomc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silvio.cursomc.domain.Cliente;
import com.silvio.cursomc.repositories.ClienteRepository;

import com.silvio.cursomc.service.exceptions.ObjectNotFoundExcepion;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {

		Cliente obj = repo.findOne(id);
		
		if(obj==null) {
			throw new ObjectNotFoundExcepion("Objeto não encontrado! Id " + id + " Tipo: " + Cliente.class.getName());
		}
		return obj;
	}

}
