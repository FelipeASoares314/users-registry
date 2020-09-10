package br.com.fas.usersregistry.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import br.com.fas.usersregistry.commons.MockObjects;

@Import({ UsersIntegrationTests.class })
public class LoginIntegrationTests extends UsersRegistryApplicationTests {
	
	@Autowired
	protected UsersIntegrationTests usersTests;

	@Test
	public void Should_Do_Login() throws Exception {
		usersTests.createUser();
		
		String json = String.format(
				"{\"cpf\":\"%s\",\"password\":\"%s\"}",
				MockObjects.mockUserNoHash().getCpf(),
				MockObjects.mockUserNoHash().getPassword()
		);
		
		mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(status().isOk())
				.andExpect(header().exists("Authorization"));
	}

}
