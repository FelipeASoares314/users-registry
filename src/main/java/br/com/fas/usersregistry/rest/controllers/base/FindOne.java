package br.com.fas.usersregistry.rest.controllers.base;

import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.fas.usersregistry.exceptions.NotFoundException;
import br.com.fas.usersregistry.services.base.FindOneService;

public interface FindOne<E, I> {

	<T extends FindOneService<E, I>> T getService();

	@GetMapping("{id}")
	public default ResponseEntity<?> findOne(@PathVariable("id") I id) {
		E entity = this.getService().findOne(id);
		
		if (Objects.isNull(entity)) {
			throw new NotFoundException("Resource " + id + " not found");
		}
		
		return ResponseEntity.ok(entity);
	}

}
