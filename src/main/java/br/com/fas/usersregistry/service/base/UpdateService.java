package br.com.fas.usersregistry.service.base;

public interface UpdateService<E> {
	
	<T extends E> T update(Object partial);

}
