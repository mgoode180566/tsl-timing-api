package com.mgoode.tsl_timing_api.event.parsers;

import com.mgoode.tsl_timing_api.event.model.entities.Event;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

interface IGpsDataParser {
	public Event parse(MultipartFile file) throws IOException;
}
