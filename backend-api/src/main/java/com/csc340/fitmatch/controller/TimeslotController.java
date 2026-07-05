package com.csc340.fitmatch.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csc340.fitmatch.entity.Timeslot;
import com.csc340.fitmatch.service.TimeslotService;

@RestController
@RequestMapping("/api/timeslots")
public class TimeslotController {

  private final TimeslotService timeslotService;

  public TimeslotController(TimeslotService timeslotService) {
    this.timeslotService = timeslotService;
  }

  @GetMapping("/trainer/{trainerId}")
  public ResponseEntity<List<Timeslot>> getTimeslotsByTrainerId(@PathVariable Long trainerId) {
    List<Timeslot> timeslots = timeslotService.getTimeslotsByTrainerId(trainerId);
    return ResponseEntity.ok(timeslots);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Timeslot> getTimeslotById(@PathVariable Long id) {
    Timeslot timeslot = timeslotService.getTimeslotById(id);
    if (timeslot == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(timeslot);
  }

  @GetMapping("/trainer/{trainerId}/available")
  public ResponseEntity<List<Timeslot>> getAvailableTimeslotsByTrainerId(@PathVariable Long trainerId) {
    List<Timeslot> availableTimeslots = timeslotService.getAvailableTimeslotsByTrainerId(trainerId);
    return ResponseEntity.ok(availableTimeslots);
  }

  @PostMapping
  public ResponseEntity<Timeslot> createTimeslot(@RequestBody Timeslot timeslot) {
    Timeslot createdTimeslot = timeslotService.createTimeslot(timeslot);
    return ResponseEntity.ok(createdTimeslot);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Timeslot> updateTimeslot(@PathVariable Long id, @RequestBody Timeslot timeslot) {
    Timeslot updatedTimeslot = timeslotService.updateTimeslot(id, timeslot);
    if (updatedTimeslot == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(updatedTimeslot);
  }

}
