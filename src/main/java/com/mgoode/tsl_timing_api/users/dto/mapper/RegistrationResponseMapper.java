package com.mgoode.tsl_timing_api.users.dto.mapper;

import com.mgoode.tsl_timing_api.users.dto.RegistrationResponseDTO;
import com.mgoode.tsl_timing_api.users.model.User;

public class RegistrationResponseMapper {
	
	public static RegistrationResponseDTO registrationResponseDTO(User user) {
		return new RegistrationResponseDTO(user.getId(),
																				user.getUserName(),
																				user.getFirstName(),
																				user.getLastName(),
																				user.getEmail(),
																				user.getRoles());
	}
}
