package com.lei.backend.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lei.backend.domain.Categoria;
import com.lei.backend.dto.CategoriaDTO;
import com.lei.backend.repositories.CategoriaRepository;
import com.lei.backend.service.exceptions.DataIntegrityException;
import com.lei.backend.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {

		Categoria obj = repo.findOne(id);
		
		if(obj==null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id " + id + " Tipo: " + Categoria.class.getName());
		}
		return obj;
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
		repo.delete(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produto");
		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linePerPage, String orgerBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linePerPage,Direction.valueOf(direction), orgerBy);
		return repo.findAll(pageRequest);
		
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getName());
		
	}
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setName(obj.getName());
		
	}
}
