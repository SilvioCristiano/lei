package com.lei.backend.resources;



import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lei.backend.domain.Advogado;
import com.lei.backend.dto.AdvogadoDTO;
import com.lei.backend.resources.utils.URL;
import com.lei.backend.service.AdvogadoService;

@RestController
@RequestMapping(value="/advogados")
public class AdvogadoResource {

	@Autowired
	private AdvogadoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Advogado> find(@PathVariable Integer id) {
		Advogado obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<AdvogadoDTO>> findPage(
			@RequestParam(value="name", defaultValue="") String name, 
			@RequestParam(value="categorias", defaultValue="") String categorias, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nomeDecoded = URL.decodeParam(name);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Advogado> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<AdvogadoDTO> listDto = list.map(obj -> new AdvogadoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/inscricao", method=RequestMethod.GET)
	public ResponseEntity<Advogado> find(@RequestParam(value="value") String inscricao) {
		Advogado obj = service.findByInscricao(inscricao);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Advogado> insert(@Valid @RequestBody AdvogadoDTO objDTO) {
		Advogado obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI url = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(url).build();
	}
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value ="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody AdvogadoDTO objDTO,@PathVariable Integer id){
		Advogado obj = service.fromDTO(objDTO);
		obj.setId(id); 
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/picture", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = service.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}
}
