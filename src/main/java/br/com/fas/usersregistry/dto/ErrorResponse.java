package br.com.fas.usersregistry.dto;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	
	public final String message;
	public final HttpStatus status;
	public final Long timestamp = System.currentTimeMillis();

	public ErrorResponse(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

}
