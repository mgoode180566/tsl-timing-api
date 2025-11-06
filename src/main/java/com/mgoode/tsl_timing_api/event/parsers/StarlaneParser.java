package com.mgoode.tsl_timing_api.event.parsers;

import com.mgoode.tsl_timing_api.event.model.dto.LapDTO;
import com.mgoode.tsl_timing_api.event.model.entities.Event;
import com.mgoode.tsl_timing_api.event.model.entities.Lap;
import com.mgoode.tsl_timing_api.event.model.entities.Sector;
import com.mgoode.tsl_timing_api.event.model.entities.Session;
import com.mgoode.tsl_timing_api.event.utils.GPSConversionUtil;
import org.ini4j.Ini;
import org.ini4j.Profile;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class StarlaneParser implements IGpsDataParser {
	@Override
	public Event parse(MultipartFile file) throws IOException {
		
		File tempFile = File.createTempFile("upload-", ".ini");
		file.transferTo(tempFile);
		
		Ini ini = new Ini(new File(tempFile.getAbsolutePath())); // <-- your file
		
		Event event = new Event();
	
		List<Sector> sectors = this.getSectors(ini);
		
		
//		Profile.Section circuitData = ini.get("TRAGUARDI");
//		double latitude = GPSConversionUtil.convertDdMmToDecimal(Double.parseDouble(circuitData.get("Lat0")), false);
//		double longitude = GPSConversionUtil.convertDdMmToDecimal(Double.parseDouble(circuitData.get("Lon0")), false);
//
//		Point point = new Point(latitude, longitude);
//
//		Sector sector = new Sector();
//		sector.setGpsLocation(point);
//		sector.setPrua(Double.parseDouble(circuitData.get("Prua0")));
//		sector.setAltitude(Double.parseDouble(circuitData.get("Altitude0")));
		
		
		
		Profile.Section sessionData = ini.get("SESSION INFO");
		if (sessionData == null) {
			sessionData = ini.get("SESSION%20INFO");
		}
		
		Session session = new Session();
		
		sectors.forEach(sector -> { sector.setSession(session); });
		session.getSectors().addAll(sectors);
		
		if (sessionData != null) {
			List<Lap> laps = new ArrayList<>();
			for (String sectionName : ini.keySet()) {
				if (sectionName.startsWith("LAP_")) {
					Profile.Section lapData = ini.get(sectionName);
					double maxSpeed = Double.parseDouble(lapData.get("MaxSpeed"));
					if (maxSpeed > 0) {
						double lapTime = parseLapTime(lapData.get("LapTime"));
						int lapNumber = laps.size();
						ArrayList<Double> splits = new ArrayList<>();
						splits.add(parseLapTime(lapData.get("Int1")));
						splits.add(parseLapTime(lapData.get("Int2")));
						splits.add(parseLapTime(lapData.get("Int3")));
						splits.add(parseLapTime(lapData.get("Int4")));
						Lap lap = new Lap(null, lapNumber, session, splits, lapTime, maxSpeed);
						laps.add(lap);
					}
				}
			}
			session.setEvent(event);
			session.setDate(sessionData.get("Date"));
			session.setTime(sessionData.get("Time"));
			session.setBike(sessionData.get("Motorbike"));
			session.setRider(sessionData.get("Rider"));
			session.getLaps().addAll(laps);
			session.setSessionName(sessionData.get("Session"));
			session.setDate(sessionData.get("Date"));
			session.setTime(sessionData.get("Time"));
			
			session.setBestLapTime(laps.stream().mapToDouble(Lap::getLapTime).min().orElse(0.0));
			
			event.getSessions().add(session);
			
			tempFile.delete();
			
			return event;
		}
		return null;
	}
	
	private List<Sector> getSectors(Ini iniFile) {
		Profile.Section circuitData = iniFile.get("TRAGUARDI");
		List<Sector> sectors = new ArrayList<>();
		
		int index = 0;
		while (circuitData.containsKey("Lat" + index) && circuitData.containsKey("Lon" + index)) {
			
			double latRaw = Double.parseDouble(circuitData.get("Lat" + index));
			double lonRaw = Double.parseDouble(circuitData.get("Lon" + index));
			
			double latitude = GPSConversionUtil.convertDdMmToDecimal(latRaw, false); // North → +
			double longitude = GPSConversionUtil.convertDdMmToDecimal(lonRaw, true); // West → -
			
			Sector sector = new Sector();
			sector.setGpsLocation(new Point(latitude, longitude));
			sector.setPrua(Double.parseDouble(circuitData.get("Prua" + index)));
			sector.setAltitude(Double.parseDouble(circuitData.get("Altitude" + index)));
			
			sectors.add(sector);
			index++;
		}
		return sectors;
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
	

