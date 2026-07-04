package com.csc340.fitmatch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csc340.fitmatch.entity.TrainingService;
import com.csc340.fitmatch.repository.TrainingServiceRepository;

@Service
public class TrainingServiceService {

  private final TrainingServiceRepository trainingServiceRepository;

  public TrainingServiceService(TrainingServiceRepository trainingServiceRepository) {
    this.trainingServiceRepository = trainingServiceRepository;
  }

  public List<TrainingService> getAllTrainingServices() {
    return trainingServiceRepository.findAll();
  }

  public TrainingService getTrainingServiceById(Long id) {
    return trainingServiceRepository.findById(id).orElse(null);
  }

  public List<TrainingService> getTrainingServicesByCategory(String category) {
    return trainingServiceRepository.findByCategoryContainingIgnoreCase(category);
  }

  public TrainingService createTrainingService(TrainingService trainingService) {
    return trainingServiceRepository.save(trainingService);
  }

  List<TrainingService> getTrainingServicesByTrainerId(Long trainerId) {
    return trainingServiceRepository.findByTrainerId(trainerId);
  }

  public TrainingService updateTrainingService(Long id, TrainingService updatedTrainingService) {
    return trainingServiceRepository.findById(id)
        .map(trainingService -> {
          trainingService.setName(updatedTrainingService.getName());
          trainingService.setDescription(updatedTrainingService.getDescription());
          trainingService.setCategory(updatedTrainingService.getCategory());
          trainingService.setPrice(updatedTrainingService.getPrice());
          return trainingServiceRepository.save(trainingService);
        })
        .orElseThrow(() -> new RuntimeException("Training service not found with id: " + id));
  }

  public void deleteTrainingService(Long id) {
    trainingServiceRepository.deleteById(id);
  }

}
