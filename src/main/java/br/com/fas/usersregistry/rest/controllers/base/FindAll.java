package br.com.fas.usersregistry.rest.controllers.base;

import java.util.Collection;

import br.com.fas.usersregistry.services.base.FindAllService;

public interface FindAll<E> {
	
	<T extends FindAllService<E>> T getService();
	
	@SuppressWarnings("unchecked")
	public default Collection<E> findAll() {
		return (Collection<E>) this.getService().findAll();
	}

}
