package br.com.fas.usersregistry.rest.controllers.base;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.fas.usersregistry.services.base.DeleteService;

public interface Delete<I> extends ControllersI {

	@DeleteMapping("{id}")
	@SuppressWarnings("unchecked")
	public default ResponseEntity<?> delete(@PathVariable("id") I id) {
		DeleteService<I> service = (DeleteService<I>) this.getService();
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
