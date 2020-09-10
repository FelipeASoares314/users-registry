package br.com.fas.usersregistry.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import br.com.fas.usersregistry.commons.MockObjects;
import br.com.fas.usersregistry.entities.Address;
import br.com.fas.usersregistry.entities.User;
import br.com.fas.usersregistry.repositories.AddressesRepository;
import br.com.fas.usersregistry.repositories.UsersRepository;

public class UsersIntegrationTests extends UsersRegistryApplicationTests {
	
	@Autowired
	protected UsersRepository usersRepository;
	
	@Autowired
	protected AddressesRepository addressesRepository;
	
	public User createUser() {
		return usersRepository.save(MockObjects.mockUser());
	}
	
	public Address createAddress(User user) {
		Address address = MockObjects.mockAddress();
		address.setUser(user);
		return addressesRepository.save(address);
	}

	@Test
	public void Should_Get_Users() throws Exception {
		mockMvc.perform(get("/users")).andExpect(status().isOk())
	    	.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	public void Should_Get_User_By_ID() throws Exception {
		User user = createUser();
		mockMvc.perform(get("/users/" + user.getId())).andExpect(status().isOk())
	    	.andExpect(jsonPath("$").isMap());
	}

	@Test
	public void Should_Create_A_User() throws Exception {
		String json = "{\"name\":\"A user\",\"cpf\":\"46759752830\",\"password\":\"APassword\"}";
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.name").value("A user"));
	}
	
	@Test
	public void Should_Update_A_User() throws Exception {
		User created = createUser();
		String json = "{\"name\":\"Other\"}";
		mockMvc.perform(patch("/users/" + created.getId()).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Other"));
	}
	
	@Test
	public void Should_Delete_A_User() throws Exception {
		User created = createUser();
		mockMvc.perform(delete("/users/" + created.getId()))
				.andExpect(status().isOk());
	}
	
	@Test
	public void Should_Add_Address_To_User() throws Exception {
		String json = "{\"city\":\"São Paulo\",\"street\":\"A Street\",\"zip\":\"A zip\",\"number\":\"a number\"}";
		User user = createUser();
		String URI = String.format("/users/%s/address", user.getId());
		mockMvc.perform(post(URI).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void Should_Delete_Address() throws Exception {
		User user = createUser();
		Address address = createAddress(user);
		
		String URI = String.format("/users/%s/address/%s", user.getId(), address.getId());
		mockMvc.perform(delete(URI))
				.andExpect(status().isOk());
	}

}
