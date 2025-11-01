package com.mgoode.tsl_timing_api.event.model.mapper;


import com.mgoode.tsl_timing_api.event.model.dto.RaceMeetingDTO;
import com.mgoode.tsl_timing_api.event.model.entities.Event;
import com.mgoode.tsl_timing_api.event.model.entities.RaceMeeting;

public class RaceMeetingMapper {
	public static RaceMeeting toEntity(RaceMeetingDTO dto) {
		RaceMeeting raceMeeting = new RaceMeeting();
		//raceMeeting.setId(dto.getId());
		raceMeeting.setMeetingName(dto.getMeetingName());
		if (dto.getEvents() != null) {
			dto.getEvents().forEach(eventDTO -> {
				Event event = EventMapper.toEntity(eventDTO);
				raceMeeting.getEvents().add(event);
			});
		}
		return raceMeeting;
	}
	
	public static RaceMeetingDTO toDTO(RaceMeeting raceMeeting) {
		RaceMeetingDTO raceMeetingDTO = new RaceMeetingDTO();
		raceMeetingDTO.setId(raceMeeting.getId());
		raceMeetingDTO.setMeetingName(raceMeeting.getMeetingName());
		
		//eventDTO.setSessions(event.getSessions().stream().map(SessionMapper::toDTO).collect(Collectors.toList()));
		return raceMeetingDTO;
	}
}
