package br.com.fas.usersregistry.rest.base;

import br.com.fas.usersregistry.service.base.SaveService;

public interface Save<E> {
	
	SaveService<E> getService();

	public default <T extends E> T save(E entity) {
		return this.getService().save(entity);
	}

}
