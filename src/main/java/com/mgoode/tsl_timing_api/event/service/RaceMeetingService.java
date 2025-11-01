package com.mgoode.tsl_timing_api.event.service;

import com.mgoode.tsl_timing_api.event.model.dto.RaceMeetingDTO;
import com.mgoode.tsl_timing_api.event.model.entities.RaceMeeting;
import com.mgoode.tsl_timing_api.event.model.mapper.RaceMeetingMapper;
import com.mgoode.tsl_timing_api.event.repository.RaceMeetingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RaceMeetingService {
	
	RaceMeetingRepository raceMeetingRepository;
	
	public RaceMeeting addRaceMeeting(RaceMeetingDTO raceMeetingDTO) {
		RaceMeeting raceMeeting = RaceMeetingMapper.toEntity(raceMeetingDTO);
		raceMeetingRepository.save(raceMeeting);
		return raceMeeting;
	}
	public Optional<RaceMeeting> getMeetingId(Long id) {
		return raceMeetingRepository.findById(id);
	}
}
