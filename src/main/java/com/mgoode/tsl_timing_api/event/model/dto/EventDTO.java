package com.mgoode.tsl_timing_api.event.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
	LocalDate eventDate;
	List<SessionDTO> sessions = new ArrayList<>();
}
