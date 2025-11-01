package com.mgoode.tsl_timing_api.users.dto;

import com.mgoode.tsl_timing_api.users.model.RoleType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserRequestDTO {
	
	private UUID id;
	
	@NotBlank(message = "Name is a required field!")
	private String userName;
	
	@NotBlank(message = "Password is a required field!")
	private String password;
	
	@NotBlank(message = "First name is a required field!")
	private String firstName;
	
	@NotBlank(message = "Last name is a required field!")
	private String lastName;
	
	@NotBlank(message = "Email is a required field!")
	@Email(message = "Must be a valid email address!")
	private String email;
	
	private List<RoleType> roles = new ArrayList<>();
}
