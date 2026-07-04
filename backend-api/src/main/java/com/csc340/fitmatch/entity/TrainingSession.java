package com.csc340.fitmatch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
  private Customer customer;

  @ManyToOne
  private TrainingService trainingService;

  @OneToOne
  private Timeslot timeslot;

  private String notes;
  private String status;
  private int duration;
  private double price;
  private String location;

  @OneToOne(mappedBy = "trainingSession")
  private Review review;

  public TrainingSession(Customer customer, TrainingService trainingService, Timeslot timeslot,
      String sessionNotes, String sessionStatus, int sesionDuration, double sessionPrice,
      String location) {
    this.customer = customer;
    this.trainingService = trainingService;
    this.timeslot = timeslot;
    this.notes = sessionNotes;
    this.status = sessionStatus;
    this.duration = sesionDuration;
    this.price = sessionPrice;
    this.location = location;
  }

}
