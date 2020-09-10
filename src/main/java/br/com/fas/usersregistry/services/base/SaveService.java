package br.com.fas.usersregistry.services.base;

public interface SaveService<E> extends Service {
	
	@SuppressWarnings("unchecked")
	default <T extends E> T save(E entity) {
		return (T) getRepository().save(beforeSave(entity));
	}

	@SuppressWarnings("unchecked")
	default <T extends E> T beforeSave(E entity) {
		return (T) entity;
	}

}
