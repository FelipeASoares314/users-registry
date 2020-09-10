package br.com.fas.usersregistry.commons;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.fas.usersregistry.entities.Address;
import br.com.fas.usersregistry.entities.User;

public class MockObjects {
	
	public static User mockUser() {
		User user = MockObjects.mockUserNoHash();
		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		user.setPassword(bcrypt.encode("password"));
		
		return user;
	}
	
	public static User mockUserNoHash() {
		User aUser = new User();
		aUser.setCpf("37761255008");
		aUser.setName("User");
		aUser.setPassword("password");
		
		return aUser;
	}
	
	public static Address mockAddress() {
		Address address = new Address();
		address.setCity("São Paulo");
		address.setComplement("");
		address.setNeighborhood("");
		address.setStreet("A Street");
		address.setZip("A zip");
		address.setNumber("A number");
		
		return address;
	}

}
