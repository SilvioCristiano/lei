package com.silvio.cursomc.service.exceptions;

public class DataIntegrityExcepion extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityExcepion(String msg) {
		super(msg);
	}
	public DataIntegrityExcepion(String msg, Throwable cause) {
		super(msg, cause);
	}
}
