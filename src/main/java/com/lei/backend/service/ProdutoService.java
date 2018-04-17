package com.lei.backend.service;


import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lei.backend.domain.Categoria;
import com.lei.backend.domain.Produto;
import com.lei.backend.dto.ProdutoDTO;
import com.lei.backend.repositories.CategoriaRepository;
import com.lei.backend.repositories.ProdutoRepository;
import com.lei.backend.security.UserSS;
import com.lei.backend.service.exceptions.AuthorizationException;
import com.lei.backend.service.exceptions.DataIntegrityException;
import com.lei.backend.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.product.profile}")
	private String prefix;

	@Value("${img.profile.size}")
	private Integer size;
	
	public Produto find(Integer id) {

		Produto obj = repo.findOne(id);
		
		if(obj==null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id " + id + " Tipo: " + Produto.class.getName());
		}
		return obj;
	}
	public Produto insert(Produto obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Produto update(Produto obj) {
		Produto newObj = find(obj.getId());
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
		return repo.findDistinctByNameContainingAndCategoriasIn(name, categorias, pageRequest);	
	}
	public URI uploadProfilePicture(MultipartFile multipartFile) {

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);

		String fileName = prefix + user.getId() + ".jpg";

		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
	
	public Produto fromDTO(ProdutoDTO objDTO) {
		return new Produto(objDTO.getId(), objDTO.getName(),objDTO.getPreco());
		
	}
	
	private void updateData(Produto newObj, Produto obj) {
		newObj.setName(obj.getName());
		newObj.setPreco(obj.getPreco());
	  //newObj.setCategorias(obj.getCategorias());
		
	}
}
