package br.com.fas.usersregistry.rest.controllers.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.fas.usersregistry.services.base.SaveService;

public interface Save<E> {
	
	<T extends SaveService<E>> T getService();

	@PostMapping
	public default ResponseEntity<E> save(E entity) {
		return new ResponseEntity<>(this.getService().save(entity), HttpStatus.CREATED);
	}

}
