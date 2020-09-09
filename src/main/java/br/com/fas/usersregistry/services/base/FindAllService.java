package br.com.fas.usersregistry.services.base;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FindAllService<E> {

	@SuppressWarnings("rawtypes")
	JpaRepository getRepository();

	@SuppressWarnings("unchecked")
	default Collection<? extends E> findAll() {
		return getRepository().findAll();
	}

}
