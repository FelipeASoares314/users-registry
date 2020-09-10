package br.com.fas.usersregistry.services.users;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fas.usersregistry.entities.Address;
import br.com.fas.usersregistry.entities.User;
import br.com.fas.usersregistry.repositories.AddressesRepository;
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
	protected AddressesRepository addressesRepository;
	
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
	
	@Transactional
	public Address addAddressToUser(Long userId, Address address) {
		if (Objects.isNull(address.getCity()))
			throw new IllegalArgumentException("City name is required");
		
		if (Objects.isNull(address.getNumber()))
			throw new IllegalArgumentException("Number is required");
		
		if (Objects.isNull(address.getStreet()))
			throw new IllegalArgumentException("Street name is required");
		
		if (Objects.isNull(address.getZip()))
			throw new IllegalArgumentException("Zip code is required");
		
		User user = this.findOne(userId);
		if (Objects.isNull(user)) throw new IllegalArgumentException("User not found!");
		address.setUser(user);
		return addressesRepository.save(address);
	}
	
	public void removeUserAddress(Long addressId) {
		addressesRepository.deleteById(addressId);		
	}

}
