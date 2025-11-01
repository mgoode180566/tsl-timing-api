package com.mgoode.tsl_timing_api.event.model.mapper;

import com.mgoode.tsl_timing_api.event.model.dto.LapDTO;
import com.mgoode.tsl_timing_api.event.model.entities.Lap;

public class LapMapper {
	public static Lap toEntity(LapDTO dto) {
		Lap lap = new Lap();
		//lap.setId(dto.getId());
		lap.setLapNumber(dto.getLapNumber());
		lap.setMaxSpeed(dto.getMaxSpeed());
		lap.setLapTime(dto.getLapTime());
		dto.getSplits().forEach(s -> {
			lap.getSplits().add(s);
		});

		// sessionId is handled in service when attaching to an existing session
		return lap;
	}
	
	public static LapDTO toDTO(Lap lap) {
		LapDTO lapDTO = new LapDTO();
		lapDTO.setId(lap.getId());
		lapDTO.setLapNumber(lap.getLapNumber());
		lapDTO.setMaxSpeed(lap.getMaxSpeed());
		lapDTO.setLapTime(lap.getLapTime());
		lap.getSplits().forEach(s -> {
			lapDTO.getSplits().add(s);
		});
		return lapDTO;
	}
}
