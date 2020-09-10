package br.com.fas.usersregistry.services.base;

import java.util.Optional;

import br.com.fas.usersregistry.utils.ClassMerge;

public interface UpdateService<E, I> extends Service {
	
	ClassMerge getMerger();
	
	@SuppressWarnings("unchecked")
	default <T extends E> T update(I id, Object partial) {
		beforeMerge(id, partial);
		Optional<T> entity = getRepository().findById(id);
		
		if (entity.isEmpty())
			throw new IllegalArgumentException("No user for id " + id);
				
		T merged = getMerger().merge(partial, entity.get());
		return (T) getRepository().save(beforeUpdate(entity.get(), merged));
	}

	@SuppressWarnings("unchecked")
	default <T extends E> T beforeUpdate(E old, E entity) {
		return (T) entity;
	}

	default void beforeMerge(I id, Object partial) {
		// Should do nothing
	}

}
