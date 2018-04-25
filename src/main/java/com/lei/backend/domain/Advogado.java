package com.lei.backend.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Advogado implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double preco;
	private String inscricao;
	private String saccional;
	private String agenda;
	private String email;
	private String cpfOuCnpj;
	@JsonIgnore
	private String senha;
	/*
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	@OneToMany(mappedBy="advogado", cascade=CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();
	*/
	

	@JsonIgnore
	@ManyToMany
	@JoinTable(name="ADVOGADO_CATEGORIA", 
	joinColumns = @JoinColumn(name="advogado_id"), 
	inverseJoinColumns = @JoinColumn(name="categoria_id"))
	private List<Categoria> categorias = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy="id.advogado")
	private Set<ItemPedido> itens =  new HashSet<>();
	
	public Advogado() {}
	public Advogado(Integer id, String name, Double preco, String inscricao, String saccional, String email, String cpfOuCnpj) {
		super();
		this.id = id;
		this.name = name;
		this.preco = preco;
		this.inscricao = inscricao;
		this.saccional = saccional;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		
	}
	@JsonIgnore
	public List<Pedido> getPedidos(){
		List<Pedido> lista = new ArrayList<>();
		for(ItemPedido x: itens) {
			lista.add(x.getPedido());
		}
		return lista;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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
	
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getSaccional() {
		return saccional;
	}
	public void setSaccional(String saccional) {
		this.saccional = saccional;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}
	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
/*	public Set<String> getTelefones() {
		return telefones;
	}
	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}*/
	@Override
	public String toString() {
		return "Advogado [id=" + id + ", name=" + name + ", preco=" + preco + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Advogado other = (Advogado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public Set<ItemPedido> getItens() {
		return itens;
	}
	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	
}
