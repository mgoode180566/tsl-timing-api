package com.mgoode.tsl_timing_api.event.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventUploadDTO {
	String eventName;
	LocalDate eventDate;
	String eventType;
	String circuit;
	String rider;
	String bike;
}
