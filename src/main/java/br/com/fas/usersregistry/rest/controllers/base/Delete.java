package br.com.fas.usersregistry.rest.controllers.base;

import br.com.fas.usersregistry.services.base.DeleteService;

public interface Delete<I> {
	
	<T extends DeleteService<I>> T getService();

	public default void delete(I id) {
		this.getService().delete(id);
	}

}
