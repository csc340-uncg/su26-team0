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
@Table(name = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Customer customer;

  @OneToOne
  private TrainingSession trainingSession;

  private int rating;
  private String comments;
  private String replyText;

  public Review(Customer customer, TrainingSession trainingSession, int rating, String comments,
      String replyText) {
    this.customer = customer;
    this.trainingSession = trainingSession;
    this.rating = rating;
    this.comments = comments;
    this.replyText = replyText;
  }

}
