package br.com.fas.usersregistry.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import br.com.fas.usersregistry.commons.MockObjects;
import br.com.fas.usersregistry.commons.MockUser;
import br.com.fas.usersregistry.entities.Address;
import br.com.fas.usersregistry.entities.User;
import br.com.fas.usersregistry.repositories.AddressesRepository;
import br.com.fas.usersregistry.repositories.UsersRepository;

public class UsersIntegrationTests extends UsersRegistryApplicationTests {
	
	@Autowired
	protected UsersRepository usersRepository;
	
	@Autowired
	protected AddressesRepository addressesRepository;
	
	protected static User logged;
	
	public User createUser() {
		return usersRepository.save(MockObjects.mockUser());
	}
	
	public Address createAddress(User user) {
		Address address = MockObjects.mockAddress();
		address.setUser(user);
		return addressesRepository.save(address);
	}
	
	@Before
	public void setup() {
		super.setup();
		User user = MockObjects.mockUser();
		user.setCpf("56962158076");
		logged = usersRepository.save(user);
	}
	
	@After
	public void dropBase() {
		addressesRepository.deleteAll();
		usersRepository.deleteAll();
	}

	@Test
	@MockUser
	public void Should_Get_Users() throws Exception {
		mockMvc.perform(get("/users")).andExpect(status().isOk())
	    	.andExpect(jsonPath("$").isArray());
	}
	
	@Test
	@MockUser
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
	public void Should_Not_Create_A_User() throws Exception {
		String json = "{\"name\":\"A user\"}";
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	@MockUser
	public void Should_Update_A_User() throws Exception {
		String json = "{\"name\":\"Other\"}";
		mockMvc.perform(patch("/users/" + logged.getId()).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Other"));
	}
	
	@Test
	@MockUser
	public void Should_Delete_A_User() throws Exception {
		mockMvc.perform(delete("/users/" + logged.getId()))
				.andExpect(status().isOk());
	}
	
	@Test
	@MockUser
	public void Should_Add_Address_To_User() throws Exception {
		String json = "{\"city\":\"São Paulo\",\"street\":\"A Street\",\"zip\":\"A zip\",\"number\":\"a number\"}";
		String URI = String.format("/users/%s/address", logged.getId());
		mockMvc.perform(post(URI).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
	}
	
	@Test
	@MockUser
	public void Should_Delete_Address() throws Exception {
		Address address = createAddress(logged);
		String URI = String.format("/users/%s/address/%s", logged.getId(), address.getId());
		mockMvc.perform(delete(URI))
				.andExpect(status().isOk());
	}
	
	@Test
	public void Should_Not_Allow_Find_User() throws Exception {
		String URI = String.format("/users/%s", "999");
		mockMvc.perform(get(URI))
				.andExpect(status().isForbidden());
	}
	
	@Test
	@MockUser
	public void Should_Not_Find_User() throws Exception {
		String URI = String.format("/users/%s", "999");
		mockMvc.perform(get(URI))
				.andExpect(status().isNotFound());
	}

}
