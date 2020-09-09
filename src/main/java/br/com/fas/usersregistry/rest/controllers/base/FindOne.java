package br.com.fas.usersregistry.rest.controllers.base;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.fas.usersregistry.services.base.FindOneService;

public interface FindOne<E, I> {

	<T extends FindOneService<E, I>> T getService();

	@GetMapping("{id}")
	public default <T extends E> T findOne(@PathVariable("id") I id) {
		return this.getService().findOne(id);
	}

}
