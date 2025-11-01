package com.mgoode.tsl_timing_api.event.repository;

import com.mgoode.tsl_timing_api.event.model.entities.RaceMeeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceMeetingRepository extends JpaRepository<RaceMeeting, Long> {}
