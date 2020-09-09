package br.com.fas.usersregistry.rest.controllers.base;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;

import br.com.fas.usersregistry.services.base.FindAllService;

public interface FindAll<E> {
	
	<T extends FindAllService<E>> T getService();
	
	@GetMapping
	@SuppressWarnings("unchecked")
	public default Collection<E> findAll() {
		return (Collection<E>) this.getService().findAll();
	}

}
