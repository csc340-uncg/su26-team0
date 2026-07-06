package com.csc340.fitmatch.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csc340.fitmatch.entity.TrainingService;
import com.csc340.fitmatch.service.TrainingServiceService;

@RestController
@RequestMapping("/api/training-services")
public class TrainingServiceController {
  private TrainingServiceService trainingServiceService;

  public TrainingServiceController(TrainingServiceService trainingService) {
    this.trainingServiceService = trainingService;
  }

  @GetMapping
  public ResponseEntity<List<TrainingService>> getAllTrainingServices() {
    List<TrainingService> trainingServices = trainingServiceService.getAllTrainingServices();
    return ResponseEntity.ok(trainingServices);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TrainingService> getTrainingServiceById(@PathVariable Long id) {
    TrainingService trainingService = trainingServiceService.getTrainingServiceById(id);
    if (trainingService == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(trainingService);
  }

  @GetMapping("/trainer/{trainerId}")
  public ResponseEntity<List<TrainingService>> getTrainingServicesByTrainerId(@PathVariable Long trainerId) {
    List<TrainingService> trainingServices = trainingServiceService.getTrainingServicesByTrainerId(trainerId);
    return ResponseEntity.ok(trainingServices);
  }

  @GetMapping("/category/{category}")
  public ResponseEntity<List<TrainingService>> getTrainingServicesByCategory(@PathVariable String category) {
    List<TrainingService> trainingServices = trainingServiceService.getTrainingServicesByCategory(category);
    return ResponseEntity.ok(trainingServices);
  }

  @GetMapping("/search")
  public ResponseEntity<List<TrainingService>> searchTrainingServicesByName(@RequestParam String query) {
    List<TrainingService> trainingServices = trainingServiceService.searchTrainingServicesByName(query);
    return ResponseEntity.ok(trainingServices);
  }

  @PostMapping
  public ResponseEntity<TrainingService> createTrainingService(@RequestBody TrainingService trainingService) {
    TrainingService createdTrainingService = trainingServiceService.createTrainingService(trainingService);
    return ResponseEntity.ok(createdTrainingService);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TrainingService> updateTrainingService(@PathVariable Long id,
      @RequestBody TrainingService trainingService) {
    TrainingService updatedTrainingService = trainingServiceService.updateTrainingService(id, trainingService);
    if (updatedTrainingService == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(updatedTrainingService);
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteTrainingService(@PathVariable Long id) {
    trainingServiceService.deleteTrainingService(id);
    return ResponseEntity.noContent().build();
  }

}
