package br.com.fas.usersregistry.services.base;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Service {
	
	@SuppressWarnings("rawtypes")
	JpaRepository getRepository();

}
