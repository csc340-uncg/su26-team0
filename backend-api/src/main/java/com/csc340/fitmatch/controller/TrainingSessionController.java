package com.csc340.fitmatch.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csc340.fitmatch.entity.TrainingSession;
import com.csc340.fitmatch.service.TrainingSessionService;

@RestController
@RequestMapping("/api/training-sessions")
public class TrainingSessionController {

  private final TrainingSessionService trainingSessionService;

  public TrainingSessionController(TrainingSessionService trainingSessionService) {
    this.trainingSessionService = trainingSessionService;
  }

  @GetMapping("/customer/{customerId}")
  public ResponseEntity<List<TrainingSession>> getTrainingSessionsByCustomer(@PathVariable Long customerId) {
    List<TrainingSession> trainingSessions = trainingSessionService.getSessionsByCustomerId(customerId);
    return ResponseEntity.ok(trainingSessions);
  }
}
