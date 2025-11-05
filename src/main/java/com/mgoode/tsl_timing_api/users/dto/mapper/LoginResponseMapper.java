package com.mgoode.tsl_timing_api.users.dto.mapper;

import com.mgoode.tsl_timing_api.users.dto.LoginResponseDTO;
import com.mgoode.tsl_timing_api.users.model.User;

public class LoginResponseMapper {
	public static LoginResponseDTO loginResponseDTO(User user) {
		return new LoginResponseDTO(user.getId(), user.getUserName(), user.getRoles());
	}
}
