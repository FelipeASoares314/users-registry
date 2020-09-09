package br.com.fas.usersregistry.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fas.usersregistry.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
