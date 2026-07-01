package com.csc340.fitmatch.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csc340.fitmatch.entity.Timeslot;

@Repository
public interface TimeSlotRepository extends JpaRepository<Timeslot, Long> {
  Timeslot findByTrainerIdAndStartTime(Long trainerId, LocalDateTime startTime);

}
