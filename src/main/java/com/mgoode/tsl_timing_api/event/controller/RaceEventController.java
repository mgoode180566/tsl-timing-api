package com.mgoode.tsl_timing_api.event.controller;

import com.mgoode.tsl_timing_api.event.model.dto.EventDTO;
import com.mgoode.tsl_timing_api.event.model.dto.EventUploadDTO;
import com.mgoode.tsl_timing_api.event.model.dto.RaceMeetingDTO;
import com.mgoode.tsl_timing_api.event.model.dto.SessionDTO;
import com.mgoode.tsl_timing_api.event.model.entities.Event;
import com.mgoode.tsl_timing_api.event.model.entities.RaceMeeting;
import com.mgoode.tsl_timing_api.event.model.entities.Session;
import com.mgoode.tsl_timing_api.event.model.mapper.EventMapper;
import com.mgoode.tsl_timing_api.event.model.mapper.SessionMapper;
import com.mgoode.tsl_timing_api.event.parsers.StarlaneParser;
import com.mgoode.tsl_timing_api.event.service.EventService;
import com.mgoode.tsl_timing_api.event.service.RaceMeetingService;
import com.mgoode.tsl_timing_api.event.service.SessionService;
import com.mgoode.tsl_timing_api.users.model.User;
import com.mgoode.tsl_timing_api.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class RaceEventController {
	
	private final EventService eventService;
	
	private final RaceMeetingService raceMeetingService;
	
	private final SessionService sessionService;

	private final StarlaneParser starlaneParser;

	private final UserService userService;
	
	@PostMapping("/upload")
	public EventDTO uploadAndParse(@Valid @ModelAttribute EventUploadDTO eventUploadDTO, @RequestParam("file") MultipartFile file, Authentication authentication) throws IOException {
			Event event = starlaneParser.parse(file);
			event.setEventName(eventUploadDTO.getEventName());
			event.setEventDate(eventUploadDTO.getEventDate());
			event.setEventType(eventUploadDTO.getEventType());
			event.getSessions().forEach(s -> {s.setBike(eventUploadDTO.getBike());
																									s.setTrack(eventUploadDTO.getCircuit());
																									s.setRider(eventUploadDTO.getRider());});
			
			User persistentUser = userService.findUser(authentication.getName());
			event.setUser(persistentUser);
			eventService.save(event);
			
			EventDTO eventDTO = EventMapper.toDTO(event);
			
			
			return eventDTO;
	}
	
	@GetMapping("/session")
	public ResponseEntity<SessionDTO> getById(@RequestParam Long id) {
		Optional<Session> session1 = sessionService.getSessionById(id);
		return sessionService.getSessionById(id)
			.map(session -> ResponseEntity.ok(SessionMapper.toDTO(session)))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/sessions")
	public ResponseEntity<List<SessionDTO>> getAll() {
		List<Session> sessions = sessionService.getAllSessions();
		return ResponseEntity.ok(sessions.stream()
			.map(SessionMapper::toDTO)
			.toList());
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<EventDTO>> getEventsByUser(Authentication authentication) {
		List<Event> events = eventService.findAllEventsByUser(authentication.getName());
		return ResponseEntity.ok(events.stream()
			.map(EventMapper::toDTO)
			.toList());
	}
	
	@GetMapping("/events")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<EventDTO>> getAllEvents() {
		List<Event> events = eventService.findAllEvents();
		return ResponseEntity.ok(events.stream()
			.map(EventMapper::toDTO)
			.toList());
	}
	
	@GetMapping("/event")
	public ResponseEntity<EventDTO> getEventById(@RequestParam Long id) {
		return eventService.findEventById(id)
			.map(event -> ResponseEntity.ok(EventMapper.toDTO(event)))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/add")
	public RaceMeeting addRaceMeeting(@RequestBody RaceMeetingDTO raceMeetingDTO) {
		return raceMeetingService.addRaceMeeting(raceMeetingDTO);
	}
	
	@GetMapping("/count")
	public long getCount() {
		return eventService.getCount();
	}
	
	@GetMapping("/Id")
	public Optional<RaceMeeting> getMeetingById(@RequestParam Long id) {
		return raceMeetingService.getMeetingId(id);
	}
}

