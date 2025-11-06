package com.mgoode.tsl_timing_api.event.model.dto;

import org.springframework.data.geo.Point;

public record SectorDTO(Long id, Point gpsLocation, double prua, double altitude) {}
