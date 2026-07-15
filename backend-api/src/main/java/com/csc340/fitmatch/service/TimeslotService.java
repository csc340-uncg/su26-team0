package com.csc340.fitmatch.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.csc340.fitmatch.entity.Timeslot;
import com.csc340.fitmatch.repository.TimeSlotRepository;

@Service
public class TimeslotService {

  private final TimeSlotRepository timeSlotRepository;

  public TimeslotService(TimeSlotRepository timeSlotRepository) {
    this.timeSlotRepository = timeSlotRepository;
  }

  public List<Timeslot> getAvailableTimeslotsByTrainerId(Long trainerId) {
    return timeSlotRepository.findByIsAvailableTrueAndTrainerIdAndStartTimeAfter(trainerId, LocalDateTime.now());
    // return timeSlotRepository.findByIsAvailableTrueAndTrainerId(trainerId);
  }

  public Timeslot getTimeslotById(Long timeslotId) {
    return timeSlotRepository.findById(timeslotId).orElse(null);
  }

  public List<Timeslot> getTimeslotsByTrainerId(Long trainerId) {
    return timeSlotRepository.findByTrainerId(trainerId);
  }

  public Timeslot createTimeslot(Timeslot timeslot) {
    return timeSlotRepository.save(timeslot);
  }

  public Timeslot updateTimeslot(Long id, Timeslot timeslot) {
    Timeslot existingTimeslot = timeSlotRepository.findById(id).orElse(null);
    if (existingTimeslot != null) {
      existingTimeslot.setStartTime(timeslot.getStartTime());
      existingTimeslot.setEndTime(timeslot.getEndTime());
      existingTimeslot.setTrainer(timeslot.getTrainer());
      existingTimeslot.setIsAvailable(timeslot.getIsAvailable());
    }
    return timeSlotRepository.save(timeslot);
  }

  public Boolean assignTimeslotToSession(Long timeslotId) {
    Timeslot timeslot = timeSlotRepository.findById(timeslotId).orElse(null);
    if (timeslot != null && timeslot.getIsAvailable()) {
      timeslot.setIsAvailable(false);
      timeSlotRepository.save(timeslot);
      return true;
    }
    return false;
  }

  public void deleteTimeslot(Long timeslotId) {
    timeSlotRepository.deleteById(timeslotId);
  }
}
