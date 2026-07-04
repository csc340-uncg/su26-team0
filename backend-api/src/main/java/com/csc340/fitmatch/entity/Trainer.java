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
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String accountStatus;

  @Column(columnDefinition = "TEXT")
  private String biography;

  private String certifications;

  private int yearsOfExperience;

  private String specialties;

  public Trainer(String name, String email, String password, String accountStatus, String biography,
      String certifications, int yearsOfExperience, String specialties) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.accountStatus = accountStatus;
    this.biography = biography;
    this.certifications = certifications;
    this.yearsOfExperience = yearsOfExperience;
    this.specialties = specialties;
  }

  @OneToMany(mappedBy = "trainer")
  private List<Timeslot> timeslots;

  @OneToMany(mappedBy = "trainer")
  private List<TrainingService> trainingServices;

}
