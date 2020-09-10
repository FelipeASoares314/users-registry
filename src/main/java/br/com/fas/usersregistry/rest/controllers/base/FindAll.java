package br.com.fas.usersregistry.rest.controllers.base;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.fas.usersregistry.services.base.FindAllService;

public interface FindAll<E> extends ControllersI {

	@GetMapping
	@SuppressWarnings("unchecked")
	public default ResponseEntity<?> findAll() {
		FindAllService<E> service = (FindAllService<E>) this.getService();
		return ResponseEntity.ok(service.findAll());
	}

}
