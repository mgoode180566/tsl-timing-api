package com.mgoode.tsl_timing_api.event.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "raceMeeting")
public class RaceMeeting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String meetingName;
	
	@OneToMany(mappedBy = "raceMeeting", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Event> events = new ArrayList<>();
}