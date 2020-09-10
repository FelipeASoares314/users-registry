package br.com.fas.usersregistry.services.base;

import java.util.Collection;

public interface FindAllService<E> extends Service {

	@SuppressWarnings("unchecked")
	default Collection<? extends E> findAll() {
		return getRepository().findAll();
	}

}
