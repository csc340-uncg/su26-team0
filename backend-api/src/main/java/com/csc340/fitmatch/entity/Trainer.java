package com.csc340.fitmatch.entity;

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
@Table(name = "trainers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long trainerId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String accountStatus;

  @Column(columnDefinition = "TEXT")
  private String biography;

  public Trainer(String name, String email, String username, String password, String accountStatus, String biography) {
    this.name = name;
    this.email = email;
    this.username = username;
    this.password = password;
    this.accountStatus = accountStatus;
    this.biography = biography;
  }

  @OneToMany(mappedBy = "trainer")
  private List<Timeslot> timeslots;

  @OneToMany(mappedBy = "trainer")
  private List<TrainingService> trainingServices;

}
