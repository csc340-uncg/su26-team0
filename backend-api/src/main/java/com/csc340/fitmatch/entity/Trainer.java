package com.csc340.fitmatch.entity;

import java.sql.Blob;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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

  @Lob
  @JdbcTypeCode(java.sql.Types.BINARY)
  @Column(columnDefinition = "bytea")
  @Basic(fetch = FetchType.LAZY)
  private Blob profilePicture;

  @Column(columnDefinition = "TEXT")
  private String biography;

  private String certifications;

  private Integer yearsOfExperience;

  private String specialties;

  public Trainer(String name, String email, String password, String accountStatus, String biography,
      String certifications, Integer yearsOfExperience, String specialties) {
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
  @JsonIgnoreProperties({ "trainer" })
  private List<Timeslot> timeslots;

  @OneToMany(mappedBy = "trainer")
  @JsonIgnoreProperties({ "trainer" })
  private List<TrainingService> trainingServices;

  @OneToMany(mappedBy = "trainer")
  @JsonIgnoreProperties({ "trainer" })
  private List<Review> reviews;

}
