package com.mgoode.tsl_timing_api.event.service;

import com.mgoode.tsl_timing_api.event.model.entities.Lap;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TSLPDFService {
	
	private final List<Lap> lapTimes = new ArrayList<>();
	
	
	private double parseTime(String t) {
		if (t.contains(":")) {
			String[] parts = t.split(":");
			return Integer.parseInt(parts[0]) * 60 + Double.parseDouble(parts[1]);
		}
		return Double.parseDouble(t);
	}
	
	public List<Lap> getLapTimes() {
		return lapTimes;
	}
	
	public void uploadPDF() {
	
	}
}
