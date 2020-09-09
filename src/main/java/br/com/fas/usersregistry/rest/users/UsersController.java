package br.com.fas.usersregistry.rest.users;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fas.usersregistry.rest.base.FindAll;
import br.com.fas.usersregistry.service.base.FindAllService;
import br.com.fas.usersregistry.service.users.UsersService;

public class UsersController implements FindAll<Object> {

	@Autowired
	protected UsersService service;

	@Override
	public FindAllService<Object> getService() {
		return service;
	}

}
