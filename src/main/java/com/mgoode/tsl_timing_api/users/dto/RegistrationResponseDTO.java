package com.mgoode.tsl_timing_api.users.dto;

import com.mgoode.tsl_timing_api.users.model.RoleType;

import java.util.List;
import java.util.UUID;

public record RegistrationResponseDTO(
	UUID id,
	String userName,
	String firstName,
	String lastName,
	String email,
	List<RoleType> roles) {}
