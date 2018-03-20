package com.silvio.cursomc.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.silvio.cursomc.domain.Categoria;
import com.silvio.cursomc.domain.Produto;
import com.silvio.cursomc.repositories.CategoriaRepository;
import com.silvio.cursomc.repositories.ProdutoRepository;
import com.silvio.cursomc.service.exceptions.DataIntegrityExcepion;
import com.silvio.cursomc.service.exceptions.ObjectNotFoundExcepion;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {

		Produto obj = repo.findOne(id);
		
		if(obj==null) {
			throw new ObjectNotFoundExcepion("Objeto não encontrado! Id " + id + " Tipo: " + Produto.class.getName());
		}
		return obj;
	}
	public Produto insert(Produto obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Produto update(Produto obj) {
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

	public List<Produto> findAll() {
		return repo.findAll();
	}
	
	public Page<Produto> findPage(Integer page, Integer linePerPage, String orgerBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linePerPage,Direction.valueOf(direction), orgerBy);
		return repo.findAll(pageRequest);
		
	}
	public Page<Produto> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAll(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(name, categorias, pageRequest);	
	}

}
