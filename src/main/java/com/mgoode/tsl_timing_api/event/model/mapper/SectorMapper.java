package com.mgoode.tsl_timing_api.event.model.mapper;

import com.mgoode.tsl_timing_api.event.model.dto.SectorDTO;
import com.mgoode.tsl_timing_api.event.model.entities.Sector;

public class SectorMapper {
	
	public static SectorDTO toDTO(Sector sector) {
		SectorDTO sectorDTO = new SectorDTO(sector.getId(), sector.getGpsLocation(), sector.getPrua(), sector.getAltitude());
		return sectorDTO;
	}
	
}
