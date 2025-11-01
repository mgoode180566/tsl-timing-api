package com.mgoode.tsl_timing_api.event.model.mapper;

import com.mgoode.tsl_timing_api.event.model.dto.EventDTO;
import com.mgoode.tsl_timing_api.event.model.entities.Event;
import com.mgoode.tsl_timing_api.event.model.entities.Session;
import com.mgoode.tsl_timing_api.users.model.User;

import java.util.stream.Collectors;

public class EventMapper {
	
	public static Event toEntity(EventDTO dto) {
		Event event = new Event();
		//event.setId(dto.getId());
		event.setEventName(dto.getEventName());
		event.setEventType(dto.getEventType());
		event.setUser(dto.getUser());
		if (dto.getSessions() != null) {
			dto.getSessions().forEach(sessionDTO -> {
				Session session = SessionMapper.toEntity(sessionDTO);
				session.setEvent(event); // fix relationship
				event.getSessions().add(session);
			});
		}
		return event;
	}
	
	public static EventDTO toDTO(Event event) {
		EventDTO eventDTO = new EventDTO();
		eventDTO.setEventName(event.getEventName());
		eventDTO.setEventType(event.getEventType());
		eventDTO.setId(event.getId());
		eventDTO.setSessions(event.getSessions().stream().map(SessionMapper::toDTO).collect(Collectors.toList()));
		return eventDTO;
	}
}
