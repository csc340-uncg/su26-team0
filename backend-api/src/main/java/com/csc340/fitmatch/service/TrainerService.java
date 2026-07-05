package com.csc340.fitmatch.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.csc340.fitmatch.dto.TrainerStatistics;
import com.csc340.fitmatch.entity.Review;
import com.csc340.fitmatch.entity.Trainer;
import com.csc340.fitmatch.entity.TrainingSession;
import com.csc340.fitmatch.repository.ReviewRepository;
import com.csc340.fitmatch.repository.TrainerRepository;
import com.csc340.fitmatch.repository.TrainingSessionRepository;

@Service
public class TrainerService {

  private final TrainerRepository trainerRepository;
  private final TrainingSessionRepository trainingSessionRepository;
  private final ReviewRepository reviewRepository;

  public TrainerService(TrainerRepository trainerRepository,
      TrainingSessionRepository trainingSessionRepository,
      ReviewRepository reviewRepository) {
    this.trainerRepository = trainerRepository;
    this.trainingSessionRepository = trainingSessionRepository;
    this.reviewRepository = reviewRepository;
  }

  public List<Trainer> getAllTrainers() {
    return trainerRepository.findAll();
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
      trainer.setName(updatedTrainer.getName());
      trainer.setEmail(updatedTrainer.getEmail());
      trainer.setPassword(updatedTrainer.getPassword());
      trainer.setBiography(updatedTrainer.getBiography());
      return trainerRepository.save(trainer);
    } else {
      throw new RuntimeException("Trainer not found with id: " + id);
    }
  }

  public Trainer updateTrainerPersonalInfo(Long id, Trainer updatedTrainer) {
    Optional<Trainer> existingTrainer = trainerRepository.findById(id);
    if (existingTrainer.isPresent()) {
      Trainer trainer = existingTrainer.get();
      if (updatedTrainer.getName() != null) {
        trainer.setName(updatedTrainer.getName());
      }
      if (updatedTrainer.getEmail() != null) {
        trainer.setEmail(updatedTrainer.getEmail());
      }
      if (updatedTrainer.getPassword() != null) {
        trainer.setPassword(updatedTrainer.getPassword());
      }
      return trainerRepository.save(trainer);
    } else {
      throw new RuntimeException("Trainer not found with id: " + id);
    }
  }

  public Trainer updateTrainerProfessionalInfo(Long id, Trainer updatedTrainer) {
    Optional<Trainer> existingTrainer = trainerRepository.findById(id);
    if (existingTrainer.isPresent()) {
      Trainer trainer = existingTrainer.get();
      if (updatedTrainer.getBiography() != null) {
        trainer.setBiography(updatedTrainer.getBiography());
      }
      if (updatedTrainer.getSpecialties() != null) {
        trainer.setSpecialties(updatedTrainer.getSpecialties());
      }
      if (updatedTrainer.getCertifications() != null) {
        trainer.setCertifications(updatedTrainer.getCertifications());
      }
      if (updatedTrainer.getYearsOfExperience() != null) {
        trainer.setYearsOfExperience(updatedTrainer.getYearsOfExperience());
      }
      return trainerRepository.save(trainer);
    } else {
      throw new RuntimeException("Trainer not found with id: " + id);
    }
  }

  public void deleteTrainer(Long id) {
    trainerRepository.deleteById(id);
  }

  public Trainer findByEmail(String email) {
    return trainerRepository.findByEmail(email);
  }

  public List<Trainer> findBySpecialty(String specialty) {
    return trainerRepository.findBySpecialtiesContainingIgnoreCase(specialty);
  }

  public TrainerStatistics getTrainerStatistics(Long trainerId) {
    Trainer trainer = trainerRepository.findById(trainerId)
        .orElseThrow(() -> new RuntimeException("Trainer not found with id: " + trainerId));

    List<TrainingSession> sessions = trainingSessionRepository.findByTrainingServiceTrainerId(trainerId);
    List<Review> reviews = reviewRepository.findByTrainerId(trainerId);

    Set<Long> customerIds = sessions.stream()
        .map(session -> session.getCustomer() != null ? session.getCustomer().getId() : null)
        .filter(java.util.Objects::nonNull)
        .collect(Collectors.toSet());

    long totalSessions = sessions.stream()
        .filter(session -> session.getStatus() == null || !"cancelled".equalsIgnoreCase(session.getStatus()))
        .count();

    double totalRevenue = sessions.stream()
        .filter(session -> session.getStatus() == null || !"cancelled".equalsIgnoreCase(session.getStatus()))
        .mapToDouble(session -> session.getTrainingService() != null ? session.getTrainingService().getPrice() : 0.0)
        .sum();

    double averageRating = reviews.stream()
        .mapToInt(Review::getRating)
        .average()
        .orElse(0.0);

    return new TrainerStatistics(
        (long) customerIds.size(),
        (long) reviews.size(),
        totalSessions,
        averageRating,
        totalRevenue);
  }

}
