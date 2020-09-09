package br.com.fas.usersregistry.services.base;

import java.util.Collection;

public interface FindAllService<E> {

	public Collection<? extends E> findAll();

}
