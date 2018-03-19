package com.silvio.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.silvio.cursomc.service.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preechimento obritório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String name;
	@NotEmpty(message="Preechimento obritório")
	@Email(message="E-mail inválido")
	private String email;
	@NotEmpty(message="Preechimento obritório")
	private String cpfOrCnpj;
	private Integer tipo;
	@NotEmpty(message="Preechimento obritório")
	private String logradouro;
	@NotEmpty(message="Preechimento obritório")
	private String numero;
	private String compremento;
	private String bairro;
	@NotEmpty(message="Preechimento obritório")
	private String cep;
	@NotEmpty(message="Preechimento obritório")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId;

	public ClienteNewDTO() {
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

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCompremento() {
		return compremento;
	}

	public void setCompremento(String compremento) {
		this.compremento = compremento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer clienteId) {
		this.cidadeId = clienteId;
	}
	

}
