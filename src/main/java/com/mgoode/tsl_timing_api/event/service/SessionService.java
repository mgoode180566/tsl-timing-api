package com.mgoode.tsl_timing_api.event.service;

import com.mgoode.tsl_timing_api.event.model.dto.SessionDTO;
import com.mgoode.tsl_timing_api.event.model.entities.Session;
import com.mgoode.tsl_timing_api.event.model.mapper.SessionMapper;
import com.mgoode.tsl_timing_api.event.repository.SessionRepository;
import lombok.AllArgsConstructor;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class SessionService {
	
	private static final Logger logger = LogManager.getLogger(SessionService.class);
	
	SessionRepository sessionRepository;
	
	
	public SessionDTO save(SessionDTO sessionDTO ) {
		Session session = SessionMapper.toEntity(sessionDTO);
		sessionRepository.save(session);
		logger.info(sessionRepository.count());
		return SessionMapper.toDTO(session);
	}
	
	public Optional<Session> getSessionById(Long id) {
		return sessionRepository.findById(id);
	}
	
	public List<Session> getAllSessions() {
		return sessionRepository.findAll();
	}
}