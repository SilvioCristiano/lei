package com.lei.backend.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lei.backend.domain.Pedido;

public class PedidoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date instante;
	public PedidoDTO() {}
	
	public PedidoDTO(Pedido pedido) {
		id = pedido.getId();
		instante = pedido.getInstante();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}
	
	
	
}
