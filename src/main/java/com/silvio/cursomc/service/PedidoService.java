package com.silvio.cursomc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silvio.cursomc.domain.Pedido;
import com.silvio.cursomc.repositories.PedidoRepository;
import com.silvio.cursomc.service.exceptions.ObjectNotFoundExcepion;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {

		Pedido obj = repo.findOne(id);
		
		if(obj==null) {
			throw new ObjectNotFoundExcepion("Objeto não encontrado! Id " + id + " Tipo: " + Pedido.class.getName());
		}
		return obj;
	}

}
