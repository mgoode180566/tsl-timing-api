package com.mgoode.tsl_timing_api.users.dto.mapper;

import com.mgoode.tsl_timing_api.users.dto.RegistrationRequestDTO;
import com.mgoode.tsl_timing_api.users.model.User;

public class UserRequestMapper {
	
	public static User toEntity(RegistrationRequestDTO userRequest) {
		User user = new User();
		user.setUserName(userRequest.getUserName());
		user.setPassword(userRequest.getPassword());
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setEmail(userRequest.getEmail());
//		user.setDateCreated(userRequest.getDateCreated());
		user.getRoles().addAll(userRequest.getRoles());
		return user;
	}
	
	public static RegistrationRequestDTO toRequestResult(User user) {
		RegistrationRequestDTO userRequest = new RegistrationRequestDTO();
		userRequest.setId(user.getId());
		userRequest.setFirstName(user.getFirstName());
		userRequest.setLastName(user.getLastName());
		userRequest.setEmail(user.getEmail());
		userRequest.setUserName(user.getUserName());
//		userRequest.setDateCreated(user.getDateCreated());
		userRequest.getRoles().addAll(user.getRoles());
		return userRequest;
	}
	
}
