package com.mgoode.tsl_timing_api.event.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lap")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Lap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	int lapNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	private Session session;
	
	List<Double> splits = new ArrayList();
	
	double lapTime;
	
	double maxSpeed;
	

	
}
