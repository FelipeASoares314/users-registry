package br.com.fas.usersregistry.services.base;

import java.util.Optional;

public interface FindOneService<E, I> extends Service {
	
	@SuppressWarnings("unchecked")
	default <T extends E> T findOne(I id) {
		Optional<T> entity = getRepository().findById(id);
		
		if (entity.isEmpty()) return null;
		
		return entity.get();
	}

}
