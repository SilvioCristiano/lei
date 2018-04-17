package com.silvio.cursomc.dto;

import com.silvio.cursomc.domain.Produto;

public class ProdutoDTO implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Double preco;
	
	public ProdutoDTO() {
	}
	public ProdutoDTO(Produto produto) {
		id = produto.getId();
		name = produto.getName();
		preco = produto.getPreco();
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
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
	
}
