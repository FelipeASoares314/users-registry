package br.com.fas.usersregistry.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fas.usersregistry.entities.Address;
import br.com.fas.usersregistry.entities.User;
import br.com.fas.usersregistry.rest.controllers.base.CrudController;
import br.com.fas.usersregistry.services.users.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController implements CrudController<User, Long> {

	@Autowired
	protected UsersService service;

	@Override
	@SuppressWarnings("unchecked")
	public UsersService getService() {
		return service;
	}

	@PostMapping("{id}/address")
	public ResponseEntity<?> addAddress(@PathVariable("id") Long userId, @RequestBody Address address) {
		Address saved = service.addAddressToUser(userId, address);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}/address/{address}")
	public ResponseEntity<?> addAddress(@PathVariable("address") Long address) {
		service.removeUserAddress(address);
		return ResponseEntity.ok().build();
	}

}
