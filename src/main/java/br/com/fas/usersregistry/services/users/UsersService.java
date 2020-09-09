package br.com.fas.usersregistry.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fas.usersregistry.entities.User;
import br.com.fas.usersregistry.repositories.UserRepository;
import br.com.fas.usersregistry.services.base.CrudService;
import br.com.fas.usersregistry.utils.ClassMerge;

@Service
public class UsersService implements CrudService<User, Long> {
	
	@Autowired
	protected ClassMerge classMerge;
	
	@Autowired
	protected UserRepository repository;

	@Override
	public UserRepository getRepository() {
		return repository;
	}

	@Override
	public ClassMerge getMerger() {
		return classMerge;
	}

}
