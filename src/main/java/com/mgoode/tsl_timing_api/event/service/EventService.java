package com.mgoode.tsl_timing_api.event.service;

import com.mgoode.tsl_timing_api.event.model.entities.Event;
import com.mgoode.tsl_timing_api.event.parsers.StarlaneParser;
import com.mgoode.tsl_timing_api.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
	private final EventRepository eventRepository;
	private final StarlaneParser starlaneParser;
	
	public Event save(Event event) {
		return eventRepository.save(event);
	}
	
	public Optional<Event> findEventById(Long id ) {
		return eventRepository.findById(id);
	}
	
	public long getCount() {
		return eventRepository.count();
	}
	
	public List<Event> findAllEvents() { return eventRepository.findAll(); }
	
	public List<Event> findAllEventsByUser(String name) { return eventRepository.findByUserUserName(name); }
}