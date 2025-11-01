package com.mgoode.tsl_timing_api.event.repository;

import com.mgoode.tsl_timing_api.event.model.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	List<Event> findByUserUserName(String name );
}
