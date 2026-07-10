package com.csc340.fitmatch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csc340.fitmatch.entity.Timeslot;
import com.csc340.fitmatch.repository.TrainingSessionRepository;
import com.csc340.fitmatch.entity.TrainingSession;

@Service
public class TrainingSessionService {

  private final TrainingSessionRepository trainingSessionRepository;

  public TrainingSessionService(TrainingSessionRepository trainingSessionRepository) {
    this.trainingSessionRepository = trainingSessionRepository;
  }

  public List<TrainingSession> getSessionsByCustomerId(Long customerId) {
    return trainingSessionRepository.findByCustomerId(customerId);
  }

  public List<TrainingSession> getSessionsByTrainingServiceId(Long trainingServiceId) {
    return trainingSessionRepository.findByTrainingServiceId(trainingServiceId);
  }

  public TrainingSession createTrainingSession(TrainingSession trainingSession) {
    return trainingSessionRepository.save(trainingSession);
  }

  public TrainingSession getSessionById(Long sessionId) {
    return trainingSessionRepository.findById(sessionId).orElse(null);
  }

  public TrainingSession updateTrainingSession(TrainingSession trainingSession) {
    TrainingSession existingSession = trainingSessionRepository.findById(trainingSession.getId()).orElse(null);
    if (existingSession != null) {
      existingSession.setCustomer(trainingSession.getCustomer());
      existingSession.setTrainingService(trainingSession.getTrainingService());
      existingSession.setTimeslot(trainingSession.getTimeslot());
      existingSession.setStatus(trainingSession.getStatus());
    }
    return trainingSessionRepository.save(trainingSession);
  }

  public void deleteTrainingSession(Long sessionId) {
    trainingSessionRepository.deleteById(sessionId);
  }

  public void cancelTrainingSession(Long sessionId) {
    TrainingSession existingSession = trainingSessionRepository.findById(sessionId).orElse(null);
    if (existingSession != null) {
      Timeslot timeslot = existingSession.getTimeslot();
      if (timeslot != null) {
        timeslot.setIsAvailable(true);
      }
      trainingSessionRepository.delete(existingSession);
    }
  }

}
