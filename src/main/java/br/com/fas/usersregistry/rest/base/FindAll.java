package br.com.fas.usersregistry.rest.base;

import java.util.Collection;

import br.com.fas.usersregistry.service.base.FindAllService;

public interface FindAll<E> {
	
	FindAllService<E> getService();
	
	@SuppressWarnings("unchecked")
	public default Collection<E> findAll() {
		return (Collection<E>) this.getService().findAll();
	}

}
