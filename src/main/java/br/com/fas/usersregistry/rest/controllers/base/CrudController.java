package br.com.fas.usersregistry.rest.controllers.base;

/**
 * The intent of this interface is to make controller classes that implement the whole CRUD less verbose.
 * 
 * @author Felipe A. Soares
 *
 * @param <E> The database entity
 * @param <I> The entity id class
 */
public interface CrudController<E, I>
	extends FindAll<E>,
			FindOne<E, I>,
			Save<E>,
			Update<E, I>,
			Delete<I> {
}
