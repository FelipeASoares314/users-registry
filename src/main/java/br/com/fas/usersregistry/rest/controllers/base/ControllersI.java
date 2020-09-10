package br.com.fas.usersregistry.rest.controllers.base;

import br.com.fas.usersregistry.services.base.Service;

public interface ControllersI {
	
	<T extends Service> T getService();

}
