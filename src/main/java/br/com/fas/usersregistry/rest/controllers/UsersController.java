package br.com.fas.usersregistry.rest.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fas.usersregistry.entities.Address;
import br.com.fas.usersregistry.entities.User;
import br.com.fas.usersregistry.rest.controllers.base.FindAll;
import br.com.fas.usersregistry.rest.controllers.base.FindOne;
import br.com.fas.usersregistry.rest.controllers.base.Save;
import br.com.fas.usersregistry.services.users.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController 
	 implements FindAll<User>,
				FindOne<User, Long>,
				Save<User> {

	@Autowired
	protected UsersService service;

	@Override
	@SuppressWarnings("unchecked")
	public UsersService getService() {
		return service;
	}
	
	@PatchMapping("{id}")
	@PreAuthorize("@customAuthorizer.isSameUser(authentication.principal.username, #id)")
	public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody Map<String, Object> partial) {
		return ResponseEntity.ok(getService().update(id, partial));
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("@customAuthorizer.isSameUser(authentication.principal.username, #id)")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		this.getService().delete(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping("{id}/address")
	@PreAuthorize("@customAuthorizer.isSameUser(authentication.principal.username, #userId)")
	public ResponseEntity<?> addAddress(@PathVariable("id") Long userId, @RequestBody Address address) {
		Address saved = service.addAddressToUser(userId, address);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}/address/{address}")
	@PreAuthorize("@customAuthorizer.isSameUser(authentication.principal.username, #userId)")
	public ResponseEntity<?> addAddress(@PathVariable("id") Long userId, @PathVariable("address") Long address) {
		service.removeUserAddress(address);
		return ResponseEntity.ok().build();
	}

}
