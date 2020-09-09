package br.com.fas.usersregistry.service.base;

public interface SaveService<E> {
	
	<T extends E> T save(E entity);

}
