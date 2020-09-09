package br.com.fas.usersregistry.services.base;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fas.usersregistry.utils.ClassMerge;

public interface UpdateService<E, I> {
	
	ClassMerge getMerger();

	@SuppressWarnings("rawtypes")
	JpaRepository getRepository();
	
	@SuppressWarnings("unchecked")
	default <T extends E> T update(I id, Object partial) {
		T entity = (T) getRepository().findById(id);
		T merged = getMerger().merge(partial, entity);
		return (T) getRepository().save(merged);
	}

}
