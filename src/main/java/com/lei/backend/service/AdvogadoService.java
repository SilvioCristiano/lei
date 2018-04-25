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
import com.lei.backend.domain.Cliente;
import com.lei.backend.domain.enums.Perfil;
import com.lei.backend.domain.Advogado;
import com.lei.backend.dto.AdvogadoDTO;
import com.lei.backend.repositories.CategoriaRepository;
import com.lei.backend.repositories.AdvogadoRepository;
import com.lei.backend.security.UserSS;
import com.lei.backend.service.exceptions.AuthorizationException;
import com.lei.backend.service.exceptions.DataIntegrityException;
import com.lei.backend.service.exceptions.ObjectNotFoundException;

@Service
public class AdvogadoService {
	@Autowired
	private AdvogadoRepository repo;
	
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
	
	public Advogado find(Integer id) {

		Advogado obj = repo.findOne(id);
		
		if(obj==null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id " + id + " Tipo: " + Advogado.class.getName());
		}
		return obj;
	}
	public Advogado insert(Advogado obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Advogado update(Advogado obj) {
		Advogado newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
		repo.delete(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui advogados");
		}
	}

	public List<Advogado> findAll() {
		return repo.findAll();
	}
	
	public Page<Advogado> findPage(Integer page, Integer linePerPage, String orgerBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linePerPage,Direction.valueOf(direction), orgerBy);
		return repo.findAll(pageRequest);
		
	}
	public Page<Advogado> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
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
	
	public Advogado fromDTO(AdvogadoDTO objDTO) {
		return new Advogado(objDTO.getId(), objDTO.getName(),objDTO.getPreco(), 
				objDTO.getInscricao(), objDTO.getSaccional(),objDTO.getEmail(),objDTO.getCpfOuCnpj());
		
	}
	
	private void updateData(Advogado newObj, Advogado obj) {
		newObj.setName(obj.getName());
		newObj.setPreco(obj.getPreco());
	  //newObj.setCategorias(obj.getCategorias());
		
	}
	
	public Advogado findByInscricao(String inscricao) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !inscricao.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}

		Advogado obj = repo.findByInscricao(inscricao);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}
}
