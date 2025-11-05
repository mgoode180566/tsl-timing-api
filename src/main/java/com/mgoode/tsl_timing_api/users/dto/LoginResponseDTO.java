package com.mgoode.tsl_timing_api.users.dto;

import com.mgoode.tsl_timing_api.users.model.RoleType;

import java.util.List;
import java.util.UUID;

public record LoginResponseDTO(UUID id,
															 String userName,
															 List<RoleType> roles) {
}
