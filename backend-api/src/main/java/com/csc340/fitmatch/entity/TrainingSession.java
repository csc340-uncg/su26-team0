package com.csc340.fitmatch.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
  private Long id;

  @ManyToOne
  @JsonIgnoreProperties({ "trainingSessions" })
  @JoinColumn(nullable = false)
  private Customer customer;

  @ManyToOne
  @JsonIgnoreProperties({ "trainingSessions" })
  @JoinColumn(nullable = false)
  private TrainingService trainingService;

  @OneToOne
  @JsonIgnoreProperties({ "trainer" })
  @JoinColumn(nullable = false)
  private Timeslot timeslot;

  private String notes;
  private String status;
  private String level;
  private String location;

  public TrainingSession(Customer customer, TrainingService trainingService, Timeslot timeslot,
      String sessionNotes, String sessionStatus, String sessionLevel, String location) {
    this.customer = customer;
    this.trainingService = trainingService;
    this.timeslot = timeslot;
    this.notes = sessionNotes;
    this.status = sessionStatus;
    this.level = sessionLevel;
    this.location = location;
  }

}
