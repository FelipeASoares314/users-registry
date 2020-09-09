package br.com.fas.usersregistry.service.base;

/**
 * The intent of this class is to make services classes that implement the whole CRUD less verbose.
 * 
 * @author Felipe A. Soares
 *
 * @param <E> The database entity
 * @param <I> The entity id class
 */
public interface CrudService<E, I>
	extends FindAllService<E>,
			FindOneService<E, I>,
			SaveService<E>,
			UpdateService<E>,
			DeleteService<I> {}
