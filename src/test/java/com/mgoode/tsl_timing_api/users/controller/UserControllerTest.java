package com.mgoode.tsl_timing_api.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgoode.tsl_timing_api.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserController.class)
class UserControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockitoBean
	UserService userService;
	
	@Autowired
	ObjectMapper objectMapper;
	
}