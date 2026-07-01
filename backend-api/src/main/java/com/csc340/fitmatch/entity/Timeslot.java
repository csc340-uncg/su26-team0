package com.csc340.fitmatch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "timeslots")
@Data
public class Timeslot {

  private Long slotId;

  @ManyToOne
  @JoinColumn(name = "trainer_id", nullable = false)
  private Trainer trainer;

  private String startTime;
  private String endTime;
  private String dayOfWeek;
  private String availabilityStatus;

}
