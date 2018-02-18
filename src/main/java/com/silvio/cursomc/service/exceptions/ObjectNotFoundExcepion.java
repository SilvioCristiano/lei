package com.silvio.cursomc.service.exceptions;

public class ObjectNotFoundExcepion extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundExcepion(String msg) {
		super(msg);
	}
	public ObjectNotFoundExcepion(String msg, Throwable cause) {
		super(msg, cause);
	}
}
