package com.mgoode.tsl_timing_api.event.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RaceMeetingDTO {
	Long id;
	String meetingName;
	List<EventDTO> events = new ArrayList<>();
}
