package com.silvio.cursomc.resources.exceptions;


import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.silvio.cursomc.service.exceptions.AuthorizationException;
import com.silvio.cursomc.service.exceptions.DataIntegrityExcepion;
import com.silvio.cursomc.service.exceptions.ObjectNotFoundExcepion;

@ControllerAdvice
public class ResourceExceptionHandler {

	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	@ExceptionHandler(ObjectNotFoundExcepion.class)
	public ResponseEntity<StandardError> objectNotFaund(ObjectNotFoundExcepion e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), timestamp);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	@ExceptionHandler(DataIntegrityExcepion.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityExcepion e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), timestamp);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){	
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", timestamp);		
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorizationException(AuthorizationException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), timestamp);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
}
