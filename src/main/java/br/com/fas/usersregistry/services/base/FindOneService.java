package br.com.fas.usersregistry.services.base;

public interface FindOneService<E, I> {
	
	public <T extends E> T findOne(I id);

}
