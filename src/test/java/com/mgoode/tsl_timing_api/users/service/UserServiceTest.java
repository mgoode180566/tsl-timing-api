package com.mgoode.tsl_timing_api.users.service;

import com.mgoode.tsl_timing_api.users.model.RoleType;
import com.mgoode.tsl_timing_api.users.model.User;
import com.mgoode.tsl_timing_api.users.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserService userService;
	
	@Test
	void findByUserName() {
		User user = new User();
		user.setUserName("mike");
		user.setPassword("password");
		user.getRoles().add(RoleType.ADMIN);
		user.setFirstName("Mike");
		user.setLastName("Goode");
		
		Mockito.when(userRepository.findByUserName(user.getUserName())).thenReturn(user);
		User foundUser = userService.findUser(user.getUserName());
		
		Assertions.assertNotNull(foundUser);
		Assertions.assertEquals(user.getUserName(), foundUser.getUserName());
		Assertions.assertTrue(foundUser.getRoles().contains(RoleType.ADMIN));
	}
	
	@Test
	void loadUser() {
		User user = new User();
		user.setUserName("mike");
		user.setPassword("password");
		user.getRoles().add(RoleType.ADMIN);
		user.setFirstName("Mike");
		user.setLastName("Goode");
		
		Mockito.when(userRepository.findByUserName(user.getUserName())).thenReturn(user);
		UserDetails userDetails = userService.loadUserByUsername("mike");
		
		Assertions.assertNotNull(userDetails);
		Assertions.assertEquals(user.getUserName(), userDetails.getUsername());
	}
	
	@Test
	void registerUser() {
		User user = new User();
		user.setUserName("mike");
		user.setPassword("password");
		user.getRoles().add(RoleType.ADMIN);
		user.setFirstName("Mike");
		user.setLastName("Goode");
		
		Mockito.when(userService.registerUser(user)).thenReturn(user);
		User registeredUser = userService.registerUser(user);
		
		Assertions.assertNotNull(registeredUser);
		Assertions.assertEquals(user.getUserName(), registeredUser.getUserName());
		Assertions.assertTrue(registeredUser.getRoles().contains(RoleType.ADMIN));
	}
	
	@Test
	void getAllUsers() {
		User user = new User();
		user.setUserName("mike");
		user.setPassword("password");
		user.getRoles().add(RoleType.ADMIN);
		user.setFirstName("Mike");
		user.setLastName("Goode");
		
		List<User> users = List.of(user);
		
		Mockito.when(userRepository.findAll()).thenReturn(users);
		List<User> allUsers = userService.getAllUsers();
		
		Assertions.assertNotNull(allUsers);
		Assertions.assertEquals(1, allUsers.size());
	}
}