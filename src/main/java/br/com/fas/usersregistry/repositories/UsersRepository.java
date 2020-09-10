package br.com.fas.usersregistry.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fas.usersregistry.entities.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByCpf(String cpf);

}
