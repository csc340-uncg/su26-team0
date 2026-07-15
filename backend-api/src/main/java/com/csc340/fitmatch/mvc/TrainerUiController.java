package com.csc340.fitmatch.mvc;

import java.util.List;
import java.util.Comparator;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import com.csc340.fitmatch.entity.Review;
import com.csc340.fitmatch.dto.TrainerStatistics;
import com.csc340.fitmatch.service.ReviewService;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csc340.fitmatch.entity.Customer;
import com.csc340.fitmatch.entity.Trainer;
import com.csc340.fitmatch.entity.TrainingService;
import com.csc340.fitmatch.service.CustomerService;
import com.csc340.fitmatch.entity.TrainingSession;
import com.csc340.fitmatch.service.TrainingSessionService;
import com.csc340.fitmatch.entity.Timeslot;
import com.csc340.fitmatch.service.TimeslotService;
import com.csc340.fitmatch.service.TrainerService;
import com.csc340.fitmatch.service.TrainingServiceService;

@Controller
@RequestMapping("/trainer")
public class TrainerUiController {

  private final TrainerService trainerService;
  private final TrainingServiceService trainingServiceService;
  private final ReviewService reviewService;
  private final CustomerService customerService;
  private final TrainingSessionService trainingSessionService;
  private final TimeslotService timeslotService;

  private final TransactionTemplate transactionTemplate;

  public TrainerUiController(TrainerService trainerService, TrainingServiceService trainingServiceService,
      ReviewService reviewService, CustomerService customerService, TrainingSessionService trainingSessionService,
      TimeslotService timeslotService, TransactionTemplate transactionTemplate) {
    this.trainerService = trainerService;
    this.trainingServiceService = trainingServiceService;
    this.reviewService = reviewService;
    this.customerService = customerService;
    this.trainingSessionService = trainingSessionService;
    this.timeslotService = timeslotService;
    this.transactionTemplate = transactionTemplate;
  }

  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("trainer", new Trainer());
    return "trainer/register";
  }

  @PostMapping("/signup")
  public String registerTrainer(Trainer trainer, MultipartFile profilePictureFile, HttpSession session) {
    trainer.setAccountStatus("active");
    Trainer created = trainerService.createTrainer(trainer);
    try {
      trainerService.saveTrainerProfilePicture(created.getId(), profilePictureFile.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
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

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/trainer/login";
  }

  @GetMapping("/picture/{trainerId}")
  public ResponseEntity<StreamingResponseBody> streamTrainerImage(@PathVariable Long trainerId) {

    StreamingResponseBody stream = outputStream -> {
      // 2. Execute the database read AND the stream copy inside the transaction
      transactionTemplate.execute(status -> {
        try (InputStream imageStream = trainerService.getTrainerImageStreamInsideTx(trainerId)) {
          StreamUtils.copy(imageStream, outputStream);
          outputStream.flush();
        } catch (Exception e) {
          e.printStackTrace();
          throw new RuntimeException("Streaming failed", e);
        }
        return null;
      });
    };

    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(stream);
  }

  @GetMapping("/dashboard")
  public String dashboard(HttpSession session, Model model) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }

    Trainer trainer = trainerService.findById(trainerId);

    List<TrainingService> services = trainingServiceService.getTrainingServicesByTrainerId(trainerId);
    TrainerStatistics stats = trainerService.getTrainerStatistics(trainerId);
    List<Review> reviews = reviewService.getReviewsByTrainerId(trainerId);

    model.addAttribute("trainer", trainer);
    model.addAttribute("services", services);
    model.addAttribute("trainerStats", stats);
    model.addAttribute("reviews", reviews);
    return "trainer/dashboard";
  }

  @PostMapping("/reviews/{reviewId}/reply")
  public String replyToReview(@PathVariable Long reviewId,
      @RequestParam(name = "replyText", required = false) String replyText,
      HttpSession session) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }

    Review review = reviewService.getReviewById(reviewId);
    if (review == null || review.getTrainer() == null || !trainerId.equals(review.getTrainer().getId())) {
      return "redirect:/trainer/dashboard";
    }

    Review update = new Review();
    update.setReplyText(replyText != null ? replyText.trim() : null);
    reviewService.updateReview(reviewId, update);
    return "redirect:/trainer/dashboard";
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

  @PostMapping("/services/add")
  public String addService(@ModelAttribute TrainingService service, HttpSession session) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }
    service.setStatus("active");

    Trainer trainer = trainerService.findById(trainerId);
    service.setTrainer(trainer);
    trainingServiceService.createTrainingService(service);

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

  @GetMapping("/services/{id}/deactivate")
  public String deactivateService(@PathVariable Long id) {
    trainingServiceService.deactivateTrainingService(id);
    return "redirect:/trainer/services";
  }

  @GetMapping("/clients")
  public String clientsPage(HttpSession session, Model model) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }

    List<Customer> clients = customerService.getCustomersByTrainerId(trainerId);
    model.addAttribute("clients", clients);
    return "trainer/view-clients";
  }

  @GetMapping("/clients/{clientId}")
  public String viewClientProfile(@PathVariable Long clientId, HttpSession session, Model model) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }

    var clientOpt = customerService.getCustomerById(clientId);

    Customer client = clientOpt.get();

    List<TrainingSession> sessions = trainingSessionService.getSessionsByCustomerId(clientId).stream()
        .filter(s -> s.getTrainingService() != null && s.getTrainingService().getTrainer() != null
            && trainerId.equals(s.getTrainingService().getTrainer().getId()))
        .collect(Collectors.toList());

    long sessionsCount = sessions.size();

    TrainingSession nextSession = sessions.stream()
        .filter(s -> s.getTimeslot() != null && s.getTimeslot().getStartTime() != null
            && s.getTimeslot().getStartTime().isAfter(LocalDateTime.now()))
        .sorted(Comparator.comparing(s -> s.getTimeslot().getStartTime()))
        .findFirst().orElse(null);

    double clientRating = reviewService.getReviewsByCustomerId(clientId).stream()
        .mapToInt(r -> r.getRating())
        .average().orElse(0.0);

    model.addAttribute("client", client);
    model.addAttribute("sessions", sessions);
    model.addAttribute("sessionsCount", sessionsCount);
    model.addAttribute("nextSession", nextSession);
    model.addAttribute("clientRating", clientRating);

    return "trainer/client-profile";
  }

  @GetMapping("/sessions/{sessionId}/complete")
  public String completeSession(@PathVariable Long sessionId, HttpSession session) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }

    TrainingSession trainingSession = trainingSessionService.getSessionById(sessionId);
    trainingSession.setStatus("Completed");
    trainingSessionService.updateTrainingSession(trainingSession);

    return "redirect:/trainer/clients/" + trainingSession.getCustomer().getId();
  }

  @PostMapping("/sessions/{sessionId}/notes")
  public String addSessionNotes(@PathVariable Long sessionId,
      @RequestParam(name = "notes", required = false) String notes,
      HttpSession httpSession) {
    Long trainerId = (Long) httpSession.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }

    TrainingSession existing = trainingSessionService.getSessionById(sessionId);
    if (existing == null || existing.getTrainingService() == null || existing.getTrainingService().getTrainer() == null
        || !trainerId.equals(existing.getTrainingService().getTrainer().getId())) {
      return "redirect:/trainer/clients";
    }

    existing.setNotes(notes);
    trainingSessionService.updateTrainingSession(existing);

    return "redirect:/trainer/clients/" + existing.getCustomer().getId();
  }

  @PostMapping("/sessions/{sessionId}/location")
  public String updateSessionLocation(@PathVariable Long sessionId,
      @RequestParam(name = "location", required = false) String location,
      HttpSession httpSession) {
    Long trainerId = (Long) httpSession.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }

    TrainingSession existing = trainingSessionService.getSessionById(sessionId);
    if (existing == null || existing.getTrainingService() == null || existing.getTrainingService().getTrainer() == null
        || !trainerId.equals(existing.getTrainingService().getTrainer().getId())
        || existing.getStatus() == null || !existing.getStatus().equals("Scheduled")) {
      return "redirect:/trainer/clients";
    }

    existing.setLocation(location != null ? location.trim() : null);
    trainingSessionService.updateTrainingSession(existing);
    return "redirect:/trainer/clients/" + existing.getCustomer().getId();
  }

  @GetMapping("/profile")
  public String profile(HttpSession session, Model model) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }

    Trainer trainer = trainerService.findById(trainerId);
    model.addAttribute("trainer", trainer);

    List<Timeslot> availableTimeslots = timeslotService.getAvailableTimeslotsByTrainerId(trainerId).stream()
        .sorted(Comparator.comparing(Timeslot::getStartTime))
        .collect(Collectors.toList());
    model.addAttribute("availableTimeslots", availableTimeslots);
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
  public String updatePersonalInfo(Trainer trainer, MultipartFile profilePictureFile, HttpSession session) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }
    if (profilePictureFile != null && !profilePictureFile.isEmpty()) {
      try {
        trainerService.saveTrainerProfilePicture(trainerId, profilePictureFile.getInputStream());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    trainerService.updateTrainerPersonalInfo(trainerId, trainer);
    return "redirect:/trainer/profile";
  }

  @PostMapping("/professional/edit")
  public String updateProfessionalInfo(Trainer trainer, HttpSession session) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }
    trainerService.updateTrainerProfessionalInfo(trainerId, trainer);
    return "redirect:/trainer/profile";
  }

  @GetMapping("/timeslots/new")
  public String newTimeslotForm(HttpSession session, Model model) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }
    model.addAttribute("timeslot", new Timeslot());
    return "trainer/new-timeslot";
  }

  @PostMapping("/timeslots/add")
  public String createTimeslot(HttpSession session, @RequestParam String startTime, @RequestParam String endTime) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }

    Trainer trainer = trainerService.findById(trainerId);

    Timeslot timeslot = new Timeslot();
    timeslot.setTrainer(trainer);
    timeslot.setStartTime(LocalDateTime.parse(startTime));
    timeslot.setEndTime(LocalDateTime.parse(endTime));
    timeslot.setIsAvailable(true);

    timeslotService.createTimeslot(timeslot);
    return "redirect:/trainer/profile";
  }

  @GetMapping("/delete-account")
  public String deleteAccount(HttpSession session) {
    Long trainerId = (Long) session.getAttribute("trainerId");
    if (trainerId == null) {
      return "redirect:/trainer/login";
    }

    trainerService.deleteTrainer(trainerId);
    session.invalidate();
    return "redirect:/";
  }
}
