package com.csc340.fitmatch.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
  private Long id;

  @ManyToOne
  @JsonIgnoreProperties({ "trainingServices" })
  @JoinColumn(nullable = false)
  private Trainer trainer;

  private String name;
  private String description;
  private double price;
  private String category;
  private String status;

  @OneToMany(mappedBy = "trainingService")
  @JsonIgnore
  private List<TrainingSession> trainingSessions;

  public TrainingService(Trainer trainer, String name, String description, double price,
      String category, String status) {
    this.trainer = trainer;
    this.name = name;
    this.description = description;
    this.price = price;
    this.category = category;
    this.status = status;
  }

}
