package com.csc340.fitmatch.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JsonIgnoreProperties({ "reviews", "trainingSessions" })
  @JoinColumn(nullable = false)
  private Customer customer;

  @ManyToOne
  @JsonIgnoreProperties({ "reviews", "timeslots" })
  @JoinColumn(nullable = false)
  private Trainer trainer;

  private int rating;
  private String comments;
  private String replyText;

  public Review(Customer customer, Trainer trainer, int rating, String comments,
      String replyText) {
    this.customer = customer;
    this.trainer = trainer;
    this.rating = rating;
    this.comments = comments;
    this.replyText = replyText;
  }

}
