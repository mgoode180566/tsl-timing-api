package com.mgoode.tsl_timing_api.event.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SessionDTO {
	Long id;
	String sessionName;
	Long eventId;
	String rider;
	String bike;
	String track;
	String date;
	String time;
	List<LapDTO> laps;
	Double bestLapTime;
	
	public SessionDTO(Long id, String sessionName, Long eventId, String rider, String bike, String track, String date, String time, List<LapDTO> laps) {
		this.id = id;
		this.sessionName = sessionName;
		this.eventId = eventId;
		this.rider = rider;
		this.bike = bike;
		this.track = track;
		this.date = date;
		this.time = time;
		this.laps = new ArrayList<>();
		this.laps.addAll(laps);
		bestLapTime = calculateBestLapTime();
	}
	
	private Double calculateBestLapTime() {
		OptionalDouble best = laps.stream().mapToDouble(LapDTO::getLapTime).min();
		if (best.isPresent()) {
			return best.getAsDouble();
		} else {
			return Double.MIN_VALUE;
		}
	}
}
