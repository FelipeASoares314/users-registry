package br.com.fas.usersregistry.rest.controllers.base;

import br.com.fas.usersregistry.services.base.FindOneService;

public interface FindOne<E, I> {

	<T extends FindOneService<E, I>> T getService();

	public default <T extends E> T findOne(I id) {
		return this.getService().findOne(id);
	}

}
