package br.com.fas.usersregistry.rest.controllers.base;

import java.util.Map;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.fas.usersregistry.services.base.UpdateService;

public interface Update<E, I> {
	
	<T extends UpdateService<E, I>> T getService();

	@PatchMapping("{id}")
	public default <T extends E> T update(@PathVariable("id") I id, Map<String, Object> partial) {
		return this.getService().update(id, partial);
	}

}
