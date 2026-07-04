package com.csc340.fitmatch.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  private String phoneNumber;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String accountStatus;

  @Column(precision = 5, scale = 2)
  private BigDecimal currentWeight;

  @Column(precision = 5, scale = 2)
  private BigDecimal goalWeight;

  private String fitnessLevel;
  private String fitnessGoals;
  private String injuriesOrHealthConcerns;

  public Customer(String name, String email, String phoneNumber , String password,
      String accountStatus, BigDecimal currentWeight, BigDecimal goalWeight, String fitnessLevel,
      String fitnessGoals, String injuriesOrHealthConcerns) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.accountStatus = accountStatus;
    this.currentWeight = currentWeight;
    this.goalWeight = goalWeight;
    this.fitnessLevel = fitnessLevel;
    this.fitnessGoals = fitnessGoals;
    this.injuriesOrHealthConcerns = injuriesOrHealthConcerns;
  }

  @OneToMany(mappedBy = "customer")
  private List<TrainingSession> trainingSessions;


  @OneToMany(mappedBy = "customer")
  private List<Review> reviews;

}
