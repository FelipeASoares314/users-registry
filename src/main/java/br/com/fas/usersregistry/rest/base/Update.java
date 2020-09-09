package br.com.fas.usersregistry.rest.base;

import java.util.Map;

import br.com.fas.usersregistry.service.base.UpdateService;

public interface Update<E> {
	
	UpdateService<E> getService();

	public default <T extends E> T update(Map<String, Object> partial) {
		return this.getService().update(partial);
	}

}
