package br.com.fas.usersregistry.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.fas.usersregistry.commons.MockObjects;
import br.com.fas.usersregistry.entities.User;
import br.com.fas.usersregistry.repositories.UsersRepository;
import br.com.fas.usersregistry.services.users.UsersService;
import br.com.fas.usersregistry.utils.ClassMerge;

@RunWith(MockitoJUnitRunner.class)
public class UserServicesTest {

	@Mock
	protected ClassMerge classMerge;

	@Mock
	protected UsersRepository repository;

	@Mock
	protected BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@InjectMocks
	protected UsersService service;

	protected static User theUser = MockObjects.mockUser();

	@Test
	public void Should_Save_User() {
		User expected = theUser;
		expected.setId(1L);
		
		String encodedPass = encoder.encode(theUser.getPassword());

		Mockito.when(encoder.encode(theUser.getPassword()))
			.thenReturn(encodedPass);
		
		Mockito.when(repository.save(theUser)).thenReturn(expected);
		User saved = service.save(theUser);

		assertNotNull(saved);
		assertEquals(expected, saved);
	}

	@Test
	public void Should_Update_User() {
		User dbUser = theUser;
		dbUser.setId(1L);

		User expected = dbUser;
		expected.setName("New User");

		Map<String, String> toUpdate = Map.of("name", expected.getName());

		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(dbUser));
		Mockito.when(classMerge.merge(toUpdate, dbUser)).thenReturn(expected);
		Mockito.when(repository.save(expected)).thenReturn(expected);

		User updated = service.update(1L, toUpdate);

		assertNotNull(updated);
		assertEquals(expected, updated);
	}

	@Test
	public void Shoul_Not_Accept_Invalid_User() {
		assertThrows(IllegalArgumentException.class, () -> {
			User user = new User();
			service.save(user);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			User user = new User();
			user.setName("A name");
			service.save(user);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			User user = new User();
			user.setPassword("");
			service.save(user);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			User user = new User();
			user.setName("A name");
			user.setPassword("pass");
			service.save(user);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			User user = new User();
			user.setName("A name");
			user.setPassword("pass");
			service.save(user);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			User user = new User();
			user.setName("A name");
			user.setPassword("password");
			user.setCpf("");
			service.save(user);
		});
	}

	@Test
	public void Should_Not_Accept_Invalid_CPF() {
		assertThrows(IllegalArgumentException.class, () -> {
			User user = new User();
			user.setId(null);
			user.setName("A user");
			user.setCpf("12312312312");
		});
	}

}
