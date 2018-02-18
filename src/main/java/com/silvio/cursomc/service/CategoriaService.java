package com.silvio.cursomc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silvio.cursomc.domain.Categoria;
import com.silvio.cursomc.repositories.CategoriaRepository;

import com.silvio.cursomc.service.exceptions.ObjectNotFoundExcepion;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {

		Categoria obj = repo.findOne(id);
		
		if(obj==null) {
			throw new ObjectNotFoundExcepion("Objeto não encontrado! Id " + id + " Tipo: " + Categoria.class.getName());
		}
		return obj;
	}

}
