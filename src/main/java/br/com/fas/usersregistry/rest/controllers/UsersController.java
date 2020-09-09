package br.com.fas.usersregistry.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
