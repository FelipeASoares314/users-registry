package br.com.fas.usersregistry.rest.controllers.base;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.fas.usersregistry.services.base.DeleteService;

public interface Delete<I> {
	
	<T extends DeleteService<I>> T getService();

	@DeleteMapping("{id}")
	public default void delete(@PathVariable("id") I id) {
		this.getService().delete(id);
	}

}
