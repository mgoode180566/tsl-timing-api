package com.mgoode.tsl_timing_api.event.model.mapper;

import com.mgoode.tsl_timing_api.event.model.dto.SessionDTO;
import com.mgoode.tsl_timing_api.event.model.entities.Lap;
import com.mgoode.tsl_timing_api.event.model.entities.Session;

import java.util.stream.Collectors;

public class SessionMapper {
	public static Session toEntity(SessionDTO dto) {
		Session session = new Session();
		session.setBestLapTime(dto.getBestLapTime());
		session.setDate(dto.getDate());
		session.setTime(dto.getTime());
		//session.setId(dto.getId());
		session.setSessionName(dto.getSessionName());
		if (dto.getLaps() != null) {
			dto.getLaps().forEach(l -> {
				Lap lap = LapMapper.toEntity(l);
				lap.setSession(session); // fix relationship
				session.getLaps().add(lap);
			});
		}
		return session;
	}
	
	public static SessionDTO toDTO(Session session) {
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setId(session.getId());
		sessionDTO.setDate(session.getDate());
		sessionDTO.setTime(sessionDTO.getTime());
		sessionDTO.setSessionName(session.getSessionName());
		sessionDTO.setLaps(session.getLaps().stream().map(LapMapper::toDTO).collect(Collectors.toList()));
		sessionDTO.setBestLapTime(session.getBestLapTime());
		return sessionDTO;
	}
}
