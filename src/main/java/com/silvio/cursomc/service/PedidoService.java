package com.silvio.cursomc.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.silvio.cursomc.domain.Pedido;
import com.silvio.cursomc.repositories.PedidoRepository;
import com.silvio.cursomc.service.exceptions.DataIntegrityExcepion;
import com.silvio.cursomc.service.exceptions.ObjectNotFoundExcepion;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {

		Pedido obj = repo.findOne(id);
		
		if(obj==null) {
			throw new ObjectNotFoundExcepion("Objeto não encontrado! Id " + id + " Tipo: " + Pedido.class.getName());
		}
		return obj;
	}
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Pedido update(Pedido obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
		repo.delete(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityExcepion("Não é possivel excluir uma categoria que possui produto");
		}
	}

	public List<Pedido> findAll() {
		return repo.findAll();
	}

}
