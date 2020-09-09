package br.com.fas.usersregistry.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fas.usersregistry.utils.BeanUtilsClassMerge;
import br.com.fas.usersregistry.utils.ClassMerge;

@Configuration
public class Beans {
	
	@Bean
	public ClassMerge classMerger() {
		return new BeanUtilsClassMerge();
	}

}
