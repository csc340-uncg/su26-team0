package com.csc340.fitmatch;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;
import java.util.List;

import com.csc340.fitmatch.entity.Trainer;
import com.csc340.fitmatch.service.TrainerService;

@Controller
public class AppController {

  private final TrainerService trainerService;

  public AppController(TrainerService trainerService) {
    this.trainerService = trainerService;
  }

  @GetMapping({ "", "/", "/index" })
  public String homePage(Model model) {
    // pick up to 3 highlighted trainers (fallback to first 3)
    List<Trainer> highlighted = trainerService.getAllTrainers().stream()
        .limit(3)
        .collect(Collectors.toList());
    model.addAttribute("highlightedTrainers", highlighted);
    return "index";
  }

}
