package br.com.fas.usersregistry.rest.controllers.base;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fas.usersregistry.services.base.UpdateService;

public interface Update<E, I> extends ControllersI {

	@PatchMapping("{id}")
	@SuppressWarnings("unchecked")
	public default ResponseEntity<?> update(@PathVariable("id") I id, @RequestBody Map<String, Object> partial) {
		UpdateService<E, I> service = (UpdateService<E, I>) this.getService();
		return ResponseEntity.ok(service.update(id, partial));
	}

}
