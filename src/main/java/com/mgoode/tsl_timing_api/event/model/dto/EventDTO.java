package com.mgoode.tsl_timing_api.event.model.dto;

import com.mgoode.tsl_timing_api.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDTO {
	Long id;
	String eventName;
	String eventType;
	String eventDate;
	User user;
	List<SessionDTO> sessions = new ArrayList<>();
}
