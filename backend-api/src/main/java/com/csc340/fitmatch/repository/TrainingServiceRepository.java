package com.csc340.fitmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csc340.fitmatch.entity.TrainingService;

@Repository
public interface TrainingServiceRepository extends JpaRepository<TrainingService, Long> {
  TrainingService findByTrainerIdAndName(Long trainerId, String name);

}
