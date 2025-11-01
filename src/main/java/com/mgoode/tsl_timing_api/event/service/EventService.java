package com.mgoode.tsl_timing_api.event.service;

import com.mgoode.tsl_timing_api.event.model.dto.EventDTO;
import com.mgoode.tsl_timing_api.event.model.entities.Event;
import com.mgoode.tsl_timing_api.event.model.mapper.EventMapper;
import com.mgoode.tsl_timing_api.event.parsers.StarlaneParser;
import com.mgoode.tsl_timing_api.event.repository.EventRepository;
import com.mgoode.tsl_timing_api.users.model.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
	private final EventRepository eventRepository;
	private final StarlaneParser starlaneParser;
	
	public EventDTO save(EventDTO eventDTO) {
		Event event = EventMapper.toEntity(eventDTO);
		eventRepository.save(event);
		return EventMapper.toDTO(event);
	}
	
	public Optional<Event> findEventById(Long id ) {
		return eventRepository.findById(id);
	}
	
	public long getCount() {
		return eventRepository.count();
	}
	
	public List<Event> findAllEvents() { return eventRepository.findAll(); }
	
	public List<Event> findAllEventsByUser(String name) { return eventRepository.findByUserUserName(name); }
	
	public EventDTO processFile(MultipartFile file) throws IOException {

		EventDTO eventDTO = starlaneParser.parse(file);
		
		return eventDTO;

	}
}
