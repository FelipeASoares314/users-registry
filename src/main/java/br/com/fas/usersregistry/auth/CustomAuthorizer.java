package br.com.fas.usersregistry.auth;

import java.util.Optional;

import br.com.fas.usersregistry.entities.User;
import br.com.fas.usersregistry.exceptions.NotFoundException;
import br.com.fas.usersregistry.repositories.UsersRepository;

public class CustomAuthorizer {
	
	protected UsersRepository repository;
	
	public CustomAuthorizer(UsersRepository repository) {
		this.repository = repository;
	}
	
	public boolean isSameUser(String cpf, Long id) {
		System.out.println("CPF " + cpf);
		System.out.println("ID " + id);
		Optional<User> user = repository.findByCpf(cpf);
		if (user.isEmpty()) throw new NotFoundException("No user for cpf " + cpf);
		return user.get().getId() == id;
	} 

}
