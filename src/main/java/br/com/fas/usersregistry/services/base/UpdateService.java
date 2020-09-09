package br.com.fas.usersregistry.services.base;

public interface UpdateService<E> {
	
	<T extends E> T update(Object partial);

}
