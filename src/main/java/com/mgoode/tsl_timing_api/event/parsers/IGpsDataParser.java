package com.mgoode.tsl_timing_api.event.parsers;

import com.mgoode.tsl_timing_api.event.model.dto.EventDTO;
import com.mgoode.tsl_timing_api.event.model.dto.SessionDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IGpsDataParser {
	public EventDTO parse(MultipartFile file) throws IOException;
}
