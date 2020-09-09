package br.com.fas.usersregistry.rest.base;

import br.com.fas.usersregistry.service.base.DeleteService;

public interface Delete<I> {
	
	DeleteService<I> getService();

	public default void delete(I id) {
		this.getService().delete(id);
	}

}
