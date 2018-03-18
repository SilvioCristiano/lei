package com.silvio.cursomc.resources;



import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.silvio.cursomc.domain.Pedido;
import com.silvio.cursomc.dto.PedidoDTO;
import com.silvio.cursomc.service.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@RequestMapping(value ="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Pedido> insert(@RequestBody Pedido obj) {
		obj = service.insert(obj);
		URI url = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(url).build();
	}
	
	@RequestMapping(value ="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Pedido obj,@PathVariable Integer id){
		obj.setId(id); 
		obj = service.update(obj);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value ="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PedidoDTO>> findAll() {
		List<Pedido> list = service.findAll();
		List<PedidoDTO> listDto = list.stream().map(obj -> new  PedidoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
}
