package br.com.fas.usersregistry.services.users;

import static java.util.Collections.emptyList;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.fas.usersregistry.entities.User;
import br.com.fas.usersregistry.repositories.UsersRepository;

public class UserAuthService implements UserDetailsService {

	protected UsersRepository repository;

	public UserAuthService(UsersRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		Optional<User> optionalUser = repository.findByCpf(cpf);
		User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException(cpf));
		return new org.springframework.security.core.userdetails.User(
				user.getCpf(), 
				user.getPassword(), 
				emptyList()
		);
	}

}
