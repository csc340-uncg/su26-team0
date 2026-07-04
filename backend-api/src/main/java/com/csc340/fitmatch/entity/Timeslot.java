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
@Table(name = "timeslots")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Timeslot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private Trainer trainer;

  private String date;
  private String startTime;
  private String endTime;
  private String dayOfWeek;
  private Boolean isAvailable;

  public Timeslot(Trainer trainer, String date, String startTime, String endTime, String dayOfWeek,
      Boolean isAvailable) {
    this.trainer = trainer;
    this.date = date;
    this.startTime = startTime;
    this.endTime = endTime;
    this.dayOfWeek = dayOfWeek;
    this.isAvailable = isAvailable;
  }

}
