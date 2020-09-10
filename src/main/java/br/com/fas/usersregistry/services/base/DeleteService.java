package br.com.fas.usersregistry.services.base;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeleteService<I> {

	@SuppressWarnings("rawtypes")
	JpaRepository getRepository();
	
	@SuppressWarnings("unchecked")
	default void delete(I id) {
		getRepository().deleteById(id);
	}

}
