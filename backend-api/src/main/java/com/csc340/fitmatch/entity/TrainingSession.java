package com.csc340.fitmatch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "training_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingSession {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long sessionId;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "trainer_id")
  private Trainer trainer;

  @ManyToOne
  @JoinColumn(name = "service_id")
  private TrainingService trainingService;

  @OneToOne
  @JoinColumn(name = "slot_id")
  private Timeslot timeslot;

  private String sessionDate;

  private String sessionNotes;
  private String sessionStatus;
  private int sesionDuration; // Duration in minutes
  private double sessionPrice; // Price for the session
  private String location;

  @OneToOne(mappedBy = "trainingSession")
  private Review review; // One-to-one relationship with Review

}
