package br.com.fas.usersregistry.rest.base;

import br.com.fas.usersregistry.service.base.FindOneService;

public interface FindOne<E, I> {

	FindOneService<E, I> getService();

	public default <T extends E> T findOne(I id) {
		return this.getService().findOne(id);
	}

}
