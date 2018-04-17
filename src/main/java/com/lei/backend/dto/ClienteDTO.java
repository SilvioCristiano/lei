package com.silvio.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.silvio.cursomc.domain.Cliente;
import com.silvio.cursomc.service.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty(message="Preechimento obritório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String name;
	@NotEmpty(message="Preechimento obritório")
	@Email(message="E-mail inválido")
	private String email;
	
	public ClienteDTO() {}
	
	public ClienteDTO(Cliente cliente) {
		id = cliente.getId();
		name = cliente.getName();
		email = cliente.getEmail();
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}




	
	
	
}
