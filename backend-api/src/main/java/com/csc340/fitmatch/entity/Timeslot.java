package com.csc340.fitmatch.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "timeslots")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Timeslot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JsonIgnoreProperties({"timeslots", "trainingServices"})
  @JoinColumn(nullable = false)
  private Trainer trainer;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime startTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime endTime;

  private Boolean isAvailable;

  public Timeslot(Trainer trainer, LocalDateTime startTime, LocalDateTime endTime,
      Boolean isAvailable) {
    this.trainer = trainer;
    this.startTime = startTime;
    this.endTime = endTime;
    this.isAvailable = isAvailable;
  }

}
