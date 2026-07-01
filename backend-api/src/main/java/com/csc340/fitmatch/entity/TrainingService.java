package com.csc340.fitmatch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "training_services")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingService {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long serviceId;

  @ManyToOne
  @JoinColumn(name = "trainer_id", nullable = false)
  private Trainer trainer;

  private String serviceName;
  private String serviceDescription;
  private double servicePrice;
  private String category;
  private String status;
  private String level;

}
