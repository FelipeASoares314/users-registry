package br.com.fas.usersregistry.rest.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.fas.usersregistry.dto.ErrorResponse;
import br.com.fas.usersregistry.exceptions.NotFoundException;

@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { IllegalArgumentException.class })
	protected ResponseEntity<?> handleBadRequest(IllegalArgumentException ex) {
		ex.printStackTrace();
		return handleResponse(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST));
	}

	@ExceptionHandler(value = { NotFoundException.class })
	protected ResponseEntity<?> handleNotFound(NotFoundException ex) {
		return handleResponse(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND));
	}
	
	@ExceptionHandler(value = { AccessDeniedException.class })
	protected ResponseEntity<?> handleForbidden(AccessDeniedException ex) {
		return handleResponse(new ErrorResponse("You dont have access to this resource", HttpStatus.FORBIDDEN));
	}

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<?> internalServerError(Exception ex) {
		return handleResponse(new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	protected ResponseEntity<?> handleResponse(ErrorResponse response) {
		return new ResponseEntity<>(response, response.status);
	}

}
