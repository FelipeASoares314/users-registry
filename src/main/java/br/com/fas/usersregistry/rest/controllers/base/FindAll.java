package br.com.fas.usersregistry.rest.controllers.base;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.fas.usersregistry.services.base.FindAllService;

public interface FindAll<E> {
	
	<T extends FindAllService<E>> T getService();
	
	@GetMapping
	public default ResponseEntity<?> findAll() {
		return ResponseEntity.ok(this.getService().findAll());
	}

}
