package br.com.fas.usersregistry.services.base;

public interface DeleteService<I> extends Service {
	
	@SuppressWarnings("unchecked")
	default void delete(I id) {
		getRepository().deleteById(id);
	}

}
