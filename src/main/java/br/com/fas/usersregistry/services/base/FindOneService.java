package br.com.fas.usersregistry.services.base;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FindOneService<E, I> {

	@SuppressWarnings("rawtypes")
	JpaRepository getRepository();
	
	@SuppressWarnings("unchecked")
	default <T extends E> T findOne(I id) {
		return (T) getRepository().findById(id);
	}

}
