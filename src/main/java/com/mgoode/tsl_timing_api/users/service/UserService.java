package com.mgoode.tsl_timing_api.users.service;

import com.mgoode.tsl_timing_api.users.model.RoleType;
import com.mgoode.tsl_timing_api.users.model.User;
import com.mgoode.tsl_timing_api.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User appUser = userRepository.findByUserName(username);
		
		if (appUser == null) {
			throw new UsernameNotFoundException("User not found: " + username);
		}
		
		return org.springframework.security.core.userdetails.User
			.withUsername(appUser.getUserName())
			.password(appUser.getPassword())
			.build();
	}

	public User registerUser(User user) {
		
		if (userRepository.findByUserName(user.getUserName()) != null) {
			throw new IllegalStateException("User name already exists");
		};
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}
	
	public User findUser(String userName) {
		if (userRepository.findByUserName(userName) == null) {
			throw new IllegalStateException("Unknown user");
		};
		return userRepository.findByUserName(userName);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}