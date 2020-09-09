package br.com.fas.usersregistry.services.base;

public interface SaveService<E> {
	
	<T extends E> T save(E entity);

}
