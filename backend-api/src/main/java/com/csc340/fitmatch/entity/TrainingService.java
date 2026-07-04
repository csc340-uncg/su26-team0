package com.csc340.fitmatch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
  private Long id;

  @ManyToOne
  private Trainer trainer;

  private String name;
  private String description;
  private double price;
  private String category;
  private String status;
  private String level;

  public TrainingService(Trainer trainer, String name, String description, double price,
      String category, String status, String level) {
    this.trainer = trainer;
    this.name = name;
    this.description = description;
    this.price = price;
    this.category = category;
    this.status = status;
    this.level = level;
  }

}
