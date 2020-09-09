package br.com.fas.usersregistry.rest.controllers.base;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.fas.usersregistry.services.base.UpdateService;

public interface Update<E, I> {
	
	<T extends UpdateService<E, I>> T getService();

	@PatchMapping("{id}")
	public default ResponseEntity<?> update(@PathVariable("id") I id, Map<String, Object> partial) {
		return ResponseEntity.ok(getService().update(id, partial));
	}

}
