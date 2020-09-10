package br.com.fas.usersregistry.rest.controllers.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fas.usersregistry.services.base.SaveService;

public interface Save<E> {
	
	<T extends SaveService<E>> T getService();

	@PostMapping
	public default ResponseEntity<?> save(@RequestBody E entity) {		
		E saved = this.getService().save(entity);		
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

}
