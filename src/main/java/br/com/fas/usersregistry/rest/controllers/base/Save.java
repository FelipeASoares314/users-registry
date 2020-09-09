package br.com.fas.usersregistry.rest.controllers.base;

import br.com.fas.usersregistry.services.base.SaveService;

public interface Save<E> {
	
	<T extends SaveService<E>> T getService();

	public default <T extends E> T save(E entity) {
		return this.getService().save(entity);
	}

}
