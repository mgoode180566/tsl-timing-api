package com.mgoode.tsl_timing_api.users.controller;

import com.mgoode.tsl_timing_api.config.JwtProperties;
import com.mgoode.tsl_timing_api.users.dto.LoginRequestDTO;
import com.mgoode.tsl_timing_api.users.dto.UserRequestDTO;
import com.mgoode.tsl_timing_api.users.dto.mapper.UserRequestMapper;
import com.mgoode.tsl_timing_api.users.model.User;
import com.mgoode.tsl_timing_api.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;

	private final AuthenticationManager authenticationManager;
	
	private final JwtProperties jwtProperties;
	
	@PostMapping("/register")
	public ResponseEntity<Object> registerUser(@Valid @RequestBody UserRequestDTO userRequest) {
		User user = userService.registerUser(UserRequestMapper.toEntity(userRequest));
		var response = new HashMap<String,Object>();
		response.put("response", UserRequestMapper.toRequestResult(user));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/login")
	public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
			User user = userService.findUser(loginRequest.getUserName());
			var loginInfo = new HashMap<String,Object>();
			loginInfo.put("token", jwtProperties.createJwtUserToken(user));
			loginInfo.put("response", UserRequestMapper.toRequestResult(user));
			return ResponseEntity.ok(loginInfo);
	}
	
	@GetMapping
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> getUsers() {
		List<User> users = userService.getAllUsers();
		var response = new HashMap<String,Object>();
		response.put("response", users.stream()
			.map(UserRequestMapper::toRequestResult)
			.toList());
		return ResponseEntity.ok(response);
	}
}