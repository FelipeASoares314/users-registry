package br.com.fas.usersregistry.services.base;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fas.usersregistry.utils.ClassMerge;

public interface UpdateService<E, I> {
	
	ClassMerge getMerger();

	@SuppressWarnings("rawtypes")
	JpaRepository getRepository();
	
	@SuppressWarnings("unchecked")
	default <T extends E> T update(I id, Object partial) {
		beforeMerge(id, partial);
		Optional<T> entity = getRepository().findById(id);
		
		if (entity.isEmpty())
			throw new IllegalArgumentException("No user for id " + id);
				
		T merged = getMerger().merge(partial, entity.get());
		return (T) getRepository().save(beforeUpdate(merged));
	}

	@SuppressWarnings("unchecked")
	default <T extends E> T beforeUpdate(E entity) {
		return (T) entity;
	}

	default void beforeMerge(I id, Object partial) {
		// Should do nothing
	}

}
