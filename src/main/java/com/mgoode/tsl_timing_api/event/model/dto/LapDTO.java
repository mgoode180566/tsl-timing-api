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
public class LapDTO {
	Long id;
	int lapNumber;
	double lapTime;
	List<Double> splits = new ArrayList<>();
	double maxSpeed;
	

//	Long sessionId;
}
