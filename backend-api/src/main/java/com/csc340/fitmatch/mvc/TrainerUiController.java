package com.csc340.fitmatch.mvc;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csc340.fitmatch.entity.Trainer;
import com.csc340.fitmatch.entity.TrainingService;
import com.csc340.fitmatch.service.TrainerService;
import com.csc340.fitmatch.service.TrainingServiceService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/trainer")
public class TrainerUiController {

  private final TrainerService trainerService;
  private final TrainingServiceService trainingServiceService;

  public TrainerUiController(TrainerService trainerService, TrainingServiceService trainingServiceService) {
    this.trainerService = trainerService;
    this.trainingServiceService = trainingServiceService;
  }

  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("trainer", new Trainer());
    return "trainer/register";
  }

  @PostMapping("/register")
  public String registerTrainer(@ModelAttribute Trainer trainer, HttpSession session) {
    Trainer created = trainerService.createTrainer(trainer);
    session.setAttribute("trainerId", created.getId());
    return "redirect:/trainer/dashboard";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping("/login")
  public String login(HttpSession session, String email, String password) {
    Trainer trainer = trainerService.findByEmail(email);
    if (trainer != null && password.equals(trainer.getPassword())) {
      session.setAttribute("trainerId", trainer.getId());
      return "redirect:/trainer/dashboard";
    }
    return "redirect:/trainer/login";
  }

  @GetMapping("/dashboard")
  public String dashboard(HttpSession session, Model model) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }

    Trainer trainer = trainerService.findById(trainerId);
    if (trainer == null) {
      return "redirect:/trainer/login";
    }

    List<TrainingService> services = trainingServiceService.getTrainingServicesByTrainerId(trainerId);
    model.addAttribute("trainer", trainer);
    model.addAttribute("services", services);
    return "trainer/dashboard";
  }

  @GetMapping("/services")
  public String getServiceManangement(HttpSession session, Model model) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }

    model.addAttribute("services", trainingServiceService.getTrainingServicesByTrainerId(trainerId));
    return "trainer/manage-services";
  }

  @GetMapping("/services/new")
  public String newServiceForm(Model model) {
    model.addAttribute("service", new TrainingService());
    return "trainer/new-service";
  }

  @PostMapping("/services")
  public String addService(@ModelAttribute TrainingService service, HttpSession session) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }

    Trainer trainer = trainerService.findById(trainerId);
    if (trainer != null) {
      service.setTrainer(trainer);
      trainingServiceService.createTrainingService(service);
    }
    return "redirect:/trainer/services";
  }

  @GetMapping("/services/{id}/edit")
  public String editServiceForm(@PathVariable Long id, Model model) {
    model.addAttribute("service", trainingServiceService.getTrainingServiceById(id));
    return "trainer/edit-service";
  }

  @PostMapping("/services/{id}/edit")
  public String updateService(@PathVariable Long id, @ModelAttribute TrainingService service) {
    trainingServiceService.updateTrainingService(id, service);
    return "redirect:/trainer/services";
  }

  @GetMapping("/clients")
  public String clientsPage() {
    return "trainer/view-clients";
  }

  @GetMapping("/clients/{clientId}")
  public String viewClientProfile(@PathVariable Long clientId, Model model) {
    model.addAttribute("clientId", clientId);
    return "trainer/client-profile";
  }

  @GetMapping("/profile")
  public String profile(HttpSession session, Model model) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }

    Trainer trainer = trainerService.findById(trainerId);
    model.addAttribute("trainer", trainer);
    return "trainer/profile";
  }

  @GetMapping("/profile/edit")
  public String editPersonalInfoForm(HttpSession session, Model model) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }

    Trainer trainer = trainerService.findById(trainerId);
    model.addAttribute("trainer", trainer);
    return "trainer/edit-details";
  }

  @PostMapping("/profile/edit")
  public String updatePersonalInfo(@ModelAttribute Trainer trainer, HttpSession session) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }
    trainerService.updateTrainerPersonalInfo(trainerId, trainer);
    return "redirect:/trainer/profile";
  }

  @GetMapping("/professional/edit")
  public String editProfessionalInfoForm(HttpSession session, Model model) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }

    Trainer trainer = trainerService.findById(trainerId);
    model.addAttribute("trainer", trainer);
    return "trainer/edit-details";
  }

  @PostMapping("/professional/edit")
  public String updateProfessionalInfo(@ModelAttribute Trainer trainer, HttpSession session) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }
    trainerService.updateTrainerProfessionalInfo(trainerId, trainer);
    return "redirect:/trainer/profile";
  }

  @PostMapping("/timeslots")
  public String addTimeslot() {
    return "redirect:/trainer/dashboard";
  }
}
