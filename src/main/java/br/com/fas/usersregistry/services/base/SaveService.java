package br.com.fas.usersregistry.services.base;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaveService<E> {

	@SuppressWarnings("rawtypes")
	JpaRepository getRepository();
	
	@SuppressWarnings("unchecked")
	default <T extends E> T save(E entity) {
		return (T) getRepository().save(beforeSave(entity));
	}

	@SuppressWarnings("unchecked")
	default <T extends E> T beforeSave(E entity) {
		return (T) entity;
	}

}
