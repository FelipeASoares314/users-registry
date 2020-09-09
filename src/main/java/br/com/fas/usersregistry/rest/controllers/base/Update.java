package br.com.fas.usersregistry.rest.controllers.base;

import java.util.Map;

import br.com.fas.usersregistry.services.base.UpdateService;

public interface Update<E> {
	
	<T extends UpdateService<E>> T getService();

	public default <T extends E> T update(Map<String, Object> partial) {
		return this.getService().update(partial);
	}

}
