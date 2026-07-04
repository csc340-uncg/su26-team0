package com.csc340.fitmatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csc340.fitmatch.entity.TrainingSession;

@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {
  TrainingSession findByCustomerIdAndTimeslotId(Long customerId, Long timeslotId);
  List<TrainingSession> findByTrainingServiceId(Long trainingServiceId);
  List<TrainingSession> findByCustomerId(Long customerId);

}
