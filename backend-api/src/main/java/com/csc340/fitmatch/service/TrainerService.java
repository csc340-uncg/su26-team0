package com.csc340.fitmatch.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.csc340.fitmatch.entity.Trainer;

import com.csc340.fitmatch.repository.TrainerRepository;

@Service
public class TrainerService {

  private final TrainerRepository trainerRepository;

  public TrainerService(TrainerRepository trainerRepository) {
    this.trainerRepository = trainerRepository;
  }

  public Optional<Trainer> findById(Long id) {
    return trainerRepository.findById(id);
  }

  public Trainer createTrainer(Trainer trainer) {
    return trainerRepository.save(trainer);
  }

  public Trainer updateTrainer(Long id, Trainer updatedTrainer) {
    Optional<Trainer> existingTrainer = trainerRepository.findById(id);
    if (existingTrainer.isPresent()) {
      Trainer trainer = existingTrainer.get();
      trainer.setUsername(updatedTrainer.getUsername());
      trainer.setEmail(updatedTrainer.getEmail());
      trainer.setPassword(updatedTrainer.getPassword());
      trainer.setBiography(updatedTrainer.getBiography());
      return trainerRepository.save(trainer);
    } else {
      throw new RuntimeException("Trainer not found with id: " + id);
    }
  }

  public void deleteTrainer(Long id) {
    trainerRepository.deleteById(id);
  }

  public Trainer findByUsername(String username) {
    return trainerRepository.findByUsername(username);
  }

}
