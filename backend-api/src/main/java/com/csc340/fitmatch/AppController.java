package com.csc340.fitmatch;

import org.springframework.http.ResponseEntity;
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
    List<Trainer> allTrainers = trainerService.getAllTrainers();
    List<Trainer> highlighted = allTrainers.subList(0, Math.min(3, allTrainers.size()));
    model.addAttribute("highlightedTrainers", highlighted);
    return "index";
  }

  @GetMapping("/live")
  public ResponseEntity<String> pulse() {
    return ResponseEntity.ok("App is live");
  }

}
