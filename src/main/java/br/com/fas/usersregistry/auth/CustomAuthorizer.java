package br.com.fas.usersregistry.auth;

import java.util.Optional;

import br.com.fas.usersregistry.entities.User;
import br.com.fas.usersregistry.repositories.UsersRepository;

public class CustomAuthorizer {
	
	protected UsersRepository repository;
	
	public CustomAuthorizer(UsersRepository repository) {
		this.repository = repository;
	}
	
	public boolean isSameUser(String principal, Long id) {
		Optional<User> user = repository.findByCpf(principal);
		return user.isPresent() && user.get().getId() == id;
	} 

}
