package com.mgoode.tsl_timing_api.event.parsers;

import com.mgoode.tsl_timing_api.event.model.dto.EventDTO;
import com.mgoode.tsl_timing_api.event.model.dto.LapDTO;
import com.mgoode.tsl_timing_api.event.model.dto.SessionDTO;
import org.ini4j.Ini;
import org.ini4j.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class StarlaneParser implements IGpsDataParser {
	@Override
	public EventDTO parse(MultipartFile file) throws IOException {
		
		File tempFile = File.createTempFile("upload-", ".ini");
		file.transferTo(tempFile);
		
		Ini ini = new Ini(new File(tempFile.getAbsolutePath())); // <-- your file
		
		EventDTO eventDTO = new EventDTO();
		eventDTO.setEventName("Emra Final");
		
		// --- Read session info ---
		Profile.Section session = ini.get("SESSION INFO");
		SessionDTO sessionDTO;
		if (session == null) {
			session = ini.get("SESSION%20INFO"); // handle encoded name
		}
		if (session != null) {
			List<LapDTO> lapDTOS = new ArrayList<>();
			for (String sectionName : ini.keySet()) {
				if (sectionName.startsWith("LAP_")) {
					//String lapNumber = sectionName.substring(sectionName.indexOf("_") + 1, sectionName.length());
					Profile.Section lap = ini.get(sectionName);
					double maxSpeed = Double.parseDouble(lap.get("MaxSpeed"));
					if (maxSpeed > 0) { // ignore invalid lap
						double lapTime = parseLapTime(lap.get("LapTime"));
						int lapNumber = lapDTOS.size();
						ArrayList<Double> splits = new ArrayList<>();
						splits.add(parseLapTime(lap.get("Int1")));
						splits.add(parseLapTime(lap.get("Int2")));
						splits.add(parseLapTime(lap.get("Int3")));
						splits.add(parseLapTime(lap.get("Int4")));
						LapDTO lapDTO = new LapDTO(0L, lapNumber, lapTime, splits, maxSpeed);
						lapDTOS.add(lapDTO);
					}
				}
			}
			sessionDTO = new SessionDTO(Long.parseLong(session.get("Run")), session.get("Event"), 0L, session.get("Rider"), session.get("Motorbike"), session.get("Track"), session.get("Date"), session.get("Time"), lapDTOS);
			sessionDTO.setSessionName(session.get("Session"));
			sessionDTO.setBike(session.get("Bike"));
			sessionDTO.setRider(session.get("Rider"));
			sessionDTO.setDate(session.get("Date"));
			sessionDTO.setTime(session.get("Time"));
			
			eventDTO.getSessions().add(sessionDTO);
			
			tempFile.delete();
			
			return eventDTO;
		}
		return null;
	}
	// Converts "mm:ss.SS" to total seconds
	private static double parseLapTime(String timeStr) {
		if (timeStr == null || timeStr.isEmpty()) return Double.MAX_VALUE;
		try {
			String[] parts = timeStr.split(":");
			double minutes = Double.parseDouble(parts[0]);
			double seconds = Double.parseDouble(parts[1]);
			return minutes * 60 + seconds;
		} catch (Exception e) {
			return Double.MAX_VALUE;
		}
	}
}
	

