package com.mgoode.tsl_timing_api.event.model.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.geo.Point;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Sector {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	Point gpsLocation;
	double prua;
	double altitude;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	Session session;
}
