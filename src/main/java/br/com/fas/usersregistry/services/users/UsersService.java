package br.com.fas.usersregistry.services.users;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fas.usersregistry.entities.User;
import br.com.fas.usersregistry.repositories.UsersRepository;
import br.com.fas.usersregistry.services.base.CrudService;
import br.com.fas.usersregistry.utils.ClassMerge;

@Service
public class UsersService implements CrudService<User, Long> {
	
	@Autowired
	protected ClassMerge classMerge;
	
	@Autowired
	protected UsersRepository repository;
	
	@Autowired
	protected BCryptPasswordEncoder encoder;

	@Override
	public UsersRepository getRepository() {
		return repository;
	}

	@Override
	public ClassMerge getMerger() {
		return classMerge;
	}
	
	protected void validateEntity(User entity) {		
		if (Objects.isNull(entity.getName()) || "".equals(entity.getName()))
			throw new IllegalArgumentException("O nome não pode ser vazio!");
		
		if (Objects.isNull(entity.getPassword()) || "".equals(entity.getPassword()))
			throw new IllegalArgumentException("A senha não pode ser vazia!");
		
		if (Objects.isNull(entity.getCpf()))
			throw new IllegalArgumentException("CPF não pode ser vazio!");
		
		if (entity.getPassword().length() < 6)
			throw new IllegalArgumentException("A senha deve conter mais que 6 caracteres!");
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T extends User> T beforeSave(User entity) {
		validateEntity(entity);
		entity.setPassword(encoder.encode(entity.getPassword()));
		return (T) entity;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends User> T beforeUpdate(User old, User entity) {
		entity.setPassword(old.getPassword());
		validateEntity(entity);
		return (T) entity;
	}

}
