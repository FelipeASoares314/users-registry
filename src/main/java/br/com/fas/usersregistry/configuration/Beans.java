package br.com.fas.usersregistry.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.fas.usersregistry.auth.CustomAuthorizer;
import br.com.fas.usersregistry.repositories.UsersRepository;
import br.com.fas.usersregistry.services.users.UserAuthService;
import br.com.fas.usersregistry.utils.BeanUtilsClassMerge;
import br.com.fas.usersregistry.utils.ClassMerge;

@Configuration
public class Beans {
	
	@Bean
	public ClassMerge classMerger() {
		return new BeanUtilsClassMerge();
	}
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public UserAuthService userAuthService(UsersRepository repository) {
		return new UserAuthService(repository);
	}
	
	@Bean
	public CustomAuthorizer customAuthorizer(UsersRepository repo) {
		return new CustomAuthorizer(repo);
	}

}
