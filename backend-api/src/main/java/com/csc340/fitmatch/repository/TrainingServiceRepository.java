package com.csc340.fitmatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csc340.fitmatch.entity.TrainingService;

@Repository
public interface TrainingServiceRepository extends JpaRepository<TrainingService, Long> {
  List<TrainingService> findByTrainerId(Long trainerId);

  List<TrainingService> findByCategoryContainingIgnoreCase(String category);

  List<TrainingService> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String nameQuery, String descriptionQuery);

}
