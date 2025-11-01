package com.mgoode.tsl_timing_api.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
	@NotBlank(message = "Name is a required field!")
	private String userName;
	
	@NotBlank(message = "Password is a required field!")
	private String password;
}
