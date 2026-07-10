package com.csc340.fitmatch.controller;

import java.util.Collections;
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

import com.csc340.fitmatch.dto.TrainerStatistics;
import com.csc340.fitmatch.entity.Trainer;
import com.csc340.fitmatch.service.TrainerService;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

  private final TrainerService trainerService;

  public TrainerController(TrainerService trainerService) {
    this.trainerService = trainerService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Trainer> getTrainerById(@PathVariable Long id) {
    Trainer trainer = trainerService.findById(id);
    if (trainer != null) {
      return ResponseEntity.ok(trainer);
    } else {
      return ResponseEntity.notFound().build();
    }

  }

  @GetMapping("/{id}/statistics")
  public ResponseEntity<TrainerStatistics> getTrainerStatistics(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(trainerService.getTrainerStatistics(id));
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  public ResponseEntity<List<Trainer>> getAllTrainers() {
    List<Trainer> trainers = trainerService.getAllTrainers();
    if (trainers.isEmpty()) {
      return ResponseEntity.ok(Collections.emptyList());
    }
    return ResponseEntity.ok(trainers);
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<Trainer> getTrainerByEmail(@PathVariable String email) {
    Trainer trainer = trainerService.findByEmail(email);
    if (trainer != null) {
      return ResponseEntity.ok(trainer);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/specialty")
  public ResponseEntity<List<Trainer>> getTrainersBySpecialty(@RequestParam String query) {
    List<Trainer> trainers = trainerService.findBySpecialty(query);
    if (trainers.isEmpty()) {
      return ResponseEntity.ok(Collections.emptyList());
    }
    return ResponseEntity.ok(trainers);
  }

  @PostMapping
  public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer) {
    Trainer createdTrainer = trainerService.createTrainer(trainer);
    return ResponseEntity.created(null).body(createdTrainer);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Trainer> updateTrainerPersonalInfo(@PathVariable Long id, @RequestBody Trainer updatedTrainer) {
    try {
      Trainer trainer = trainerService.updateTrainerPersonalInfo(id, updatedTrainer);
      return ResponseEntity.ok(trainer);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}/professional")
  public ResponseEntity<Trainer> updateTrainerProfessionalInfo(@PathVariable Long id,
      @RequestBody Trainer updatedTrainer) {
    try {
      Trainer trainer = trainerService.updateTrainerProfessionalInfo(id, updatedTrainer);
      return ResponseEntity.ok(trainer);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
    trainerService.deleteTrainer(id);
    return ResponseEntity.noContent().build();
  }

}
