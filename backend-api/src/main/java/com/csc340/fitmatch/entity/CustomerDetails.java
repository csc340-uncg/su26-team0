package com.csc340.fitmatch.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {

  @OneToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @Column(precision = 5, scale = 2)
  private BigDecimal currentWeight;

  @Column(precision = 5, scale = 2)
  private BigDecimal goalWeight;

  private String fitnessLevel;
  private String fitnessGoals;
  private String injuriesOrHealthConcerns;

}
