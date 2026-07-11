package com.csc340.fitmatch.mvc;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csc340.fitmatch.entity.Customer;
import com.csc340.fitmatch.entity.Review;
import com.csc340.fitmatch.entity.Timeslot;
import com.csc340.fitmatch.entity.Trainer;
import com.csc340.fitmatch.entity.TrainingService;
import com.csc340.fitmatch.entity.TrainingSession;
import com.csc340.fitmatch.service.CustomerService;
import com.csc340.fitmatch.service.ReviewService;
import com.csc340.fitmatch.service.TimeslotService;
import com.csc340.fitmatch.service.TrainingServiceService;
import com.csc340.fitmatch.service.TrainingSessionService;
import com.csc340.fitmatch.service.TrainerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerUiController {

  private final CustomerService customerService;
  private final TrainerService trainerService;
  private final TrainingSessionService trainingSessionService;
  private final TrainingServiceService trainingServiceService;
  private final TimeslotService timeslotService;
  private final ReviewService reviewService;

  public CustomerUiController(CustomerService customerService, TrainerService trainerService,
      TrainingSessionService trainingSessionService, TrainingServiceService trainingServiceService,
      TimeslotService timeslotService) {
    this(customerService, trainerService, trainingSessionService, trainingServiceService, timeslotService, null);
  }

  @Autowired
  public CustomerUiController(CustomerService customerService, TrainerService trainerService,
      TrainingSessionService trainingSessionService, TrainingServiceService trainingServiceService,
      TimeslotService timeslotService, ReviewService reviewService) {
    this.customerService = customerService;
    this.trainerService = trainerService;
    this.trainingSessionService = trainingSessionService;
    this.trainingServiceService = trainingServiceService;
    this.timeslotService = timeslotService;
    this.reviewService = reviewService;
  }

  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("customer", new Customer());
    return "register";
  }

  @PostMapping("/signup")
  public String registerCustomer(Customer customer, HttpSession session) {
    customer.setAccountStatus("active");
    Customer created = customerService.createCustomer(customer);
    session.setAttribute("customerId", created.getId());
    return "redirect:/customer/profile";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping("/login")
  public String login(HttpSession session, String email, String password) {
    Customer customer = customerService.findByEmail(email);
    if (customer != null && password.equals(customer.getPassword())) {
      session.setAttribute("customerId", customer.getId());
      return "redirect:/customer/browse";
    }
    return "redirect:/customer/login";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/customer/login";
  }


  @GetMapping("/browse")
  public String browse(Model model, HttpSession session) {
    Long customerId = (Long) session.getAttribute("customerId");

    if (customerId == null) {
      return "redirect:/customer/login";
    }

    String customerGoals = "";
    List<Trainer> specialtyMatches = new ArrayList<>();
    LinkedHashSet<Long> seenTrainerIds = new LinkedHashSet<>();

    Optional<Customer> customer = customerService.getCustomerById(customerId);
    if (customer.isPresent()) {
      customerGoals = customer.get().getFitnessGoals() != null ? customer.get().getFitnessGoals() : "";
    }

    String normalizedGoals = customerGoals.toLowerCase();
    if (!normalizedGoals.isEmpty()) {
      for (String goal : normalizedGoals.split("[ ,;]+")) {
        if (!goal.isBlank()) {
          for (Trainer trainer : trainerService.findBySpecialty(goal)) {
            if (trainer != null && seenTrainerIds.add(trainer.getId())) {
              specialtyMatches.add(trainer);
            }
          }
        }
      }
    }

    model.addAttribute("trainers", trainerService.getAllTrainers());
    model.addAttribute("specialtyMatches", specialtyMatches);
    model.addAttribute("customerGoals", customerGoals);
    return "customer/browse-trainers";

  }

  @GetMapping("/trainers/{trainerId}")
  public String viewTrainerProfile(@PathVariable Long trainerId, Model model) {
    model.addAttribute("trainer", trainerService.findById(trainerId));
    model.addAttribute("trainerAvgRating", trainerService.getTrainerStatistics(trainerId).averageTrainerRating());
    model.addAttribute("services", trainingServiceService.getTrainingServicesByTrainerId(trainerId));
    return "customer/trainer-profile";
  }

  @GetMapping("/trainers/{trainerId}/book")
  public String bookSessionPage(@PathVariable Long trainerId, Model model, HttpSession session) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/customer/login";
    }

    Trainer trainer = trainerService.findById(trainerId);
    if (trainer == null) {
      return "redirect:/customer/browse";
    }

    List<Timeslot> timeslots = timeslotService.getAvailableTimeslotsByTrainerId(trainerId);
    model.addAttribute("trainer", trainer);
    model.addAttribute("timeslots", timeslots);
    model.addAttribute("services", trainingServiceService.getActiveTrainingServicesByTrainerId(trainerId));
    return "customer/book-session";
  }

  @PostMapping("/sessions/book")
  public String bookSession(@RequestParam Long trainerId, @RequestParam Long serviceId,
      @RequestParam Long timeslotId, @RequestParam String level, @RequestParam String notes, HttpSession session) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/customer/login";
    }

    Optional<Customer> customer = customerService.getCustomerById(customerId);
    Trainer trainer = trainerService.findById(trainerId);
    Timeslot timeslot = timeslotService.getTimeslotById(timeslotId);
    if (customer.isEmpty() || trainer == null || timeslot == null
        || !Boolean.TRUE.equals(timeslot.getIsAvailable())) {
      return "redirect:/customer/trainers/" + trainerId + "/book";
    }

    TrainingService selectedService = trainingServiceService.getTrainingServiceById(serviceId);
    if (selectedService == null || !trainerId.equals(selectedService.getTrainer().getId())) {
      return "redirect:/customer/trainers/" + trainerId + "/book";
    }

    TrainingSession booking = new TrainingSession();
    booking.setCustomer(customer.get());
    booking.setTrainingService(selectedService);
    booking.setTimeslot(timeslot);
    booking.setStatus("Scheduled");
    booking.setLocation("To be confirmed");
    booking.setNotes(notes);
    booking.setLevel(level);
    trainingSessionService.createTrainingSession(booking);
    timeslotService.assignTimeslotToSession(timeslotId);
    return "redirect:/customer/sessions";
  }

  @GetMapping("/sessions")
  public String sessionManagement(HttpSession session, Model model) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/customer/login";
    }

    model.addAttribute("sessions", trainingSessionService.getSessionsByCustomerId(customerId));
    model.addAttribute("reviews", reviewService.getReviewsByCustomerId(customerId));

    return "customer/my-sessions";
  }

  @PostMapping("/sessions/{sessionId}/cancel")
  public String cancelSession(@PathVariable Long sessionId) {
    trainingSessionService.cancelTrainingSession(sessionId);
    return "redirect:/customer/sessions";
  }

  @GetMapping("/trainers/{trainerId}/review")
  public String showReviewForm(@PathVariable Long trainerId, Model model, HttpSession session) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/customer/login";
    }

    Trainer trainer = trainerService.findById(trainerId);

    model.addAttribute("trainer", trainer);
    model.addAttribute("review", new Review());
    return "customer/review";
  }

  @PostMapping("/trainers/{trainerId}/review")
  public String submitReview(@PathVariable Long trainerId, @RequestParam int rating,
      @RequestParam String comments, HttpSession session) {

    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null || reviewService == null) {
      return "redirect:/customer/login";
    }

    Trainer trainer = trainerService.findById(trainerId);
    Optional<Customer> customer = customerService.getCustomerById(customerId);
    if (customer.isEmpty() || trainer == null) {
      return "redirect:/customer/sessions";
    }

    int safeRating = Math.max(1, Math.min(5, rating));
    Review review = new Review(customer.get(), trainer, safeRating, comments, null);
    reviewService.createReview(review);
    return "redirect:/customer/sessions";
  }

  @GetMapping("/profile")
  public String profile(HttpSession session, Model model) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/customer/login";
    }

    customerService.getCustomerById(customerId).ifPresent(customer -> model.addAttribute("customer", customer));
    return "customer/profile";
  }

  @GetMapping("/profile/edit")
  public String editPersonalInfoForm(HttpSession session, Model model) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/customer/login";
    }

    model.addAttribute("section", "personal");
    customerService.getCustomerById(customerId).ifPresent(customer -> model.addAttribute("customer", customer));
    return "customer/edit-details";
  }

  @PostMapping("/profile/edit")
  public String updatePersonalInfo(@ModelAttribute Customer customer, HttpSession session) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/customer/login";
    }
    customerService.updatePersonalInfo(customerId, customer);
    return "redirect:/customer/profile";
  }

  @GetMapping("/fitness/edit")
  public String editFitnessInfo(HttpSession session, Model model) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/customer/login";
    }

    model.addAttribute("section", "fitness");
    customerService.getCustomerById(customerId).ifPresent(customer -> model.addAttribute("customer", customer));
    return "customer/edit-details";
  }

  @PostMapping("/fitness/edit")
  public String updateFitnessInfo(@ModelAttribute Customer customer, HttpSession session) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/customer/login";
    }
    customerService.updateFitnessInfo(customerId, customer);
    return "redirect:/customer/profile";
  }

  @GetMapping("/delete")
  public String deleteAccount(HttpSession session) {
    Long customerId = (Long) session.getAttribute("customerId");
    if (customerId == null) {
      return "redirect:/customer/login";
    }

    customerService.deleteCustomer(customerId);
    session.invalidate();
    return "redirect:/";
  }

}
