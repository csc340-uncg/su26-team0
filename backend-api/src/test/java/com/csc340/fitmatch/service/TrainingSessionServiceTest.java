package com.csc340.fitmatch.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.csc340.fitmatch.entity.Timeslot;
import com.csc340.fitmatch.entity.TrainingSession;
import com.csc340.fitmatch.repository.TrainingSessionRepository;

class TrainingSessionServiceTest {

  @Test
  void cancelTrainingSessionDeletesSessionAndReleasesTimeslot() {
    TrainingSessionRepository repository = mock(TrainingSessionRepository.class);
    TrainingSessionService service = new TrainingSessionService(repository);

    TrainingSession session = new TrainingSession();
    Timeslot timeslot = new Timeslot();
    timeslot.setIsAvailable(false);
    session.setTimeslot(timeslot);
    session.setStatus("Scheduled");

    when(repository.findById(10L)).thenReturn(Optional.of(session));

    service.cancelTrainingSession(10L);

    assertTrue(Boolean.TRUE.equals(timeslot.getIsAvailable()));
    verify(repository).delete(session);
  }
}
