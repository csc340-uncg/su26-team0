package com.csc340.fitmatch.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.csc340.fitmatch.dto.TrainerStatistics;
import com.csc340.fitmatch.entity.Review;
import com.csc340.fitmatch.entity.Timeslot;
import com.csc340.fitmatch.entity.Trainer;
import com.csc340.fitmatch.entity.TrainingSession;
import com.csc340.fitmatch.repository.ReviewRepository;
import com.csc340.fitmatch.repository.TimeSlotRepository;
import com.csc340.fitmatch.repository.TrainerRepository;
import com.csc340.fitmatch.repository.TrainingServiceRepository;
import com.csc340.fitmatch.repository.TrainingSessionRepository;

@Service
public class TrainerService {

  private final TrainerRepository trainerRepository;
  private final TrainingSessionRepository trainingSessionRepository;
  private final ReviewRepository reviewRepository;
  private final TimeSlotRepository timeSlotRepository;
  private final TrainingServiceRepository trainingServiceRepository;

  public TrainerService(TrainerRepository trainerRepository,
      TrainingSessionRepository trainingSessionRepository,
      ReviewRepository reviewRepository,
      TimeSlotRepository timeSlotRepository,
      TrainingServiceRepository trainingServiceRepository) {
    this.trainerRepository = trainerRepository;
    this.trainingSessionRepository = trainingSessionRepository;
    this.reviewRepository = reviewRepository;
    this.timeSlotRepository = timeSlotRepository;
    this.trainingServiceRepository = trainingServiceRepository;
  }

  public List<Trainer> getAllTrainers() {
    return trainerRepository.findAll();
  }

  public Trainer findById(Long id) {
    return trainerRepository.findById(id).orElse(null);
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
      if (updatedTrainer.getName() != null && !updatedTrainer.getName().trim().isEmpty()) {
        trainer.setName(updatedTrainer.getName().trim());
      }
      if (updatedTrainer.getEmail() != null && !updatedTrainer.getEmail().trim().isEmpty()) {
        trainer.setEmail(updatedTrainer.getEmail().trim());
      }
      if (updatedTrainer.getPassword() != null && !updatedTrainer.getPassword().trim().isEmpty()) {
        trainer.setPassword(updatedTrainer.getPassword());
      }
      if (updatedTrainer.getProfilePicture() != null ) {
        trainer.setProfilePicture(updatedTrainer.getProfilePicture());
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
      if (updatedTrainer.getBiography() != null && !updatedTrainer.getBiography().trim().isEmpty()) {
        trainer.setBiography(updatedTrainer.getBiography().trim());
      }
      if (updatedTrainer.getSpecialties() != null && !updatedTrainer.getSpecialties().trim().isEmpty()) {
        trainer.setSpecialties(updatedTrainer.getSpecialties().trim());
      }
      if (updatedTrainer.getCertifications() != null && !updatedTrainer.getCertifications().trim().isEmpty()) {
        trainer.setCertifications(updatedTrainer.getCertifications().trim());
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
    // Delete training sessions for this trainer
    List<TrainingSession> sessions = trainingSessionRepository.findByTrainingServiceTrainerId(id);
    if (sessions != null && !sessions.isEmpty()) {
      trainingSessionRepository.deleteAll(sessions);
    }

    // Delete reviews for this trainer
    List<Review> reviews = reviewRepository.findByTrainerId(id);
    if (reviews != null && !reviews.isEmpty()) {
      reviewRepository.deleteAll(reviews);
    }

    // Delete timeslots for this trainer
    List<Timeslot> timeslots = timeSlotRepository.findByTrainerId(id);
    if (timeslots != null && !timeslots.isEmpty()) {
      timeSlotRepository.deleteAll(timeslots);
    }

    // Delete training services offered by this trainer
    List<com.csc340.fitmatch.entity.TrainingService> services = trainingServiceRepository.findByTrainerId(id);
    if (services != null && !services.isEmpty()) {
      trainingServiceRepository.deleteAll(services);
    }

    // Finally delete the trainer
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
        .mapToDouble(
            session -> session.getTrainingService() != null ? session.getTrainingService().getPrice().doubleValue()
                : 0.0)
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
