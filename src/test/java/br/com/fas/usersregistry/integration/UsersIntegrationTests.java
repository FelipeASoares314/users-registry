package br.com.fas.usersregistry.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.fas.usersregistry.configuration.Beans;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Beans.class })
public class UsersIntegrationTests {

	protected MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext wac;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void Should_Create_A_User() throws Exception {
		String json = "{\"name\":\"A user\",\"cpf\":\"46759752830\",\"password\":\"APassword\"}";
		
	    mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
	    	.andExpect(status().isCreated())
	    	.andExpect(jsonPath("$.name").value("A user"));
	}

}
