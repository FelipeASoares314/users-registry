package br.com.fas.usersregistry.services.base;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FindOneService<E, I> {

	@SuppressWarnings("rawtypes")
	JpaRepository getRepository();
	
	@SuppressWarnings("unchecked")
	default <T extends E> T findOne(I id) {
		Optional<T> entity = getRepository().findById(id);
		
		if (entity.isEmpty()) return null;
		
		return entity.get();
	}

}
