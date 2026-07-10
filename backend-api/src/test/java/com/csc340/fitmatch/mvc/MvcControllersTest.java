package com.csc340.fitmatch.mvc;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ExtendedModelMap;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class MvcControllersTest {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  void springContextLoadsWithMvcControllers() {
    assertNotNull(applicationContext);
    assertNotNull(applicationContext.getBean(CustomerUiController.class));
    assertNotNull(applicationContext.getBean(TrainerUiController.class));
  }

  @Test
  void customerSessionsPagePopulatesModelWithSessions() {
    CustomerService customerService = mock(CustomerService.class);
    TrainerService trainerService = mock(TrainerService.class);
    TrainingSessionService trainingSessionService = mock(TrainingSessionService.class);
    TrainingServiceService trainingServiceService = mock(TrainingServiceService.class);
    TimeslotService timeslotService = mock(TimeslotService.class);
    ReviewService reviewService = mock(ReviewService.class);

    CustomerUiController controller = new CustomerUiController(customerService, trainerService,
        trainingSessionService, trainingServiceService, timeslotService, reviewService);

    TrainingSession session = new TrainingSession();
    session.setStatus("Scheduled");
    session.setLocation("Sunset Studio");
    session.setLevel("Intermediate");

    when(trainingSessionService.getSessionsByCustomerId(1L)).thenReturn(List.of(session));

    ExtendedModelMap model = new ExtendedModelMap();
    MockHttpSession httpSession = new MockHttpSession();
    httpSession.setAttribute("customerId", 1L);

    String viewName = controller.sessionManagement(httpSession, model);

    assertEquals("customer/my-sessions", viewName);
    assertEquals(List.of(session), model.getAttribute("sessions"));
  }

  @Test
  void completedSessionReviewPagePopulatesTrainerAndSession() {
    CustomerService customerService = mock(CustomerService.class);
    TrainerService trainerService = mock(TrainerService.class);
    TrainingSessionService trainingSessionService = mock(TrainingSessionService.class);
    TrainingServiceService trainingServiceService = mock(TrainingServiceService.class);
    TimeslotService timeslotService = mock(TimeslotService.class);
    ReviewService reviewService = mock(ReviewService.class);

    CustomerUiController controller = new CustomerUiController(customerService, trainerService,
        trainingSessionService, trainingServiceService, timeslotService, reviewService);

    Customer customer = new Customer();
    customer.setId(1L);

    Trainer trainer = new Trainer();
    trainer.setId(7L);
    trainer.setName("Sarah Kim");

    when(customerService.getCustomerById(1L)).thenReturn(Optional.of(customer));
    when(trainerService.findById(7L)).thenReturn(trainer);

    ExtendedModelMap model = new ExtendedModelMap();
    MockHttpSession httpSession = new MockHttpSession();
    httpSession.setAttribute("customerId", 1L);

    String viewName = controller.showReviewForm(7L, model, httpSession);

    assertEquals("customer/review", viewName);
    assertEquals(new Review(), model.getAttribute("review"));
    assertEquals(trainer, model.getAttribute("trainer"));
  }

  @Test
  void customerSessionsPagePopulatesCustomerReviews() {
    CustomerService customerService = mock(CustomerService.class);
    TrainerService trainerService = mock(TrainerService.class);
    TrainingSessionService trainingSessionService = mock(TrainingSessionService.class);
    TrainingServiceService trainingServiceService = mock(TrainingServiceService.class);
    TimeslotService timeslotService = mock(TimeslotService.class);
    ReviewService reviewService = mock(ReviewService.class);

    CustomerUiController controller = new CustomerUiController(customerService, trainerService,
        trainingSessionService, trainingServiceService, timeslotService, reviewService);

    Review review = new Review();
    review.setComments("Great experience");

    when(reviewService.getReviewsByCustomerId(1L)).thenReturn(List.of(review));

    ExtendedModelMap model = new ExtendedModelMap();
    MockHttpSession httpSession = new MockHttpSession();
    httpSession.setAttribute("customerId", 1L);

    String viewName = controller.sessionManagement(httpSession, model);

    assertEquals("customer/my-sessions", viewName);
    assertEquals(List.of(review), model.getAttribute("reviews"));
  }

  @Test
  void browsePageRecommendsTrainerMatchingCustomerGoals() {
    CustomerService customerService = mock(CustomerService.class);
    TrainerService trainerService = mock(TrainerService.class);
    TrainingSessionService trainingSessionService = mock(TrainingSessionService.class);
    TrainingServiceService trainingServiceService = mock(TrainingServiceService.class);
    TimeslotService timeslotService = mock(TimeslotService.class);

    CustomerUiController controller = new CustomerUiController(customerService, trainerService,
        trainingSessionService, trainingServiceService, timeslotService);

    Customer customer = new Customer();
    customer.setId(1L);
    customer.setFitnessGoals("Strength training and muscle gain");

    Trainer weightLossTrainer = new Trainer();
    weightLossTrainer.setId(2L);
    weightLossTrainer.setName("Maya Chen");
    weightLossTrainer.setSpecialties("Weight Loss");

    Trainer strengthTrainer = new Trainer();
    strengthTrainer.setId(3L);
    strengthTrainer.setName("Nina Patel");
    strengthTrainer.setSpecialties("Strength Training");

    when(customerService.getCustomerById(1L)).thenReturn(Optional.of(customer));
    when(trainerService.getAllTrainers()).thenReturn(List.of(weightLossTrainer, strengthTrainer));
    when(trainerService.findBySpecialty("strength")).thenReturn(List.of(strengthTrainer));
    when(trainerService.findBySpecialty("training")).thenReturn(List.of(strengthTrainer));
    when(trainerService.findBySpecialty("muscle")).thenReturn(List.of(strengthTrainer));
    when(trainerService.findBySpecialty("gain")).thenReturn(List.of(strengthTrainer));

    ExtendedModelMap model = new ExtendedModelMap();
    MockHttpSession httpSession = new MockHttpSession();
    httpSession.setAttribute("customerId", 1L);

    String viewName = controller.browse(model, httpSession);

    assertEquals("customer/browse-trainers", viewName);
    assertEquals(List.of(strengthTrainer), model.getAttribute("specialtyMatches"));
    assertEquals("Strength training and muscle gain", model.getAttribute("customerGoals"));
  }

  @Test
  void bookingPagePopulatesTrainerAndAvailableTimeslots() {
    CustomerService customerService = mock(CustomerService.class);
    TrainerService trainerService = mock(TrainerService.class);
    TrainingSessionService trainingSessionService = mock(TrainingSessionService.class);
    TrainingServiceService trainingServiceService = mock(TrainingServiceService.class);
    TimeslotService timeslotService = mock(TimeslotService.class);

    CustomerUiController controller = new CustomerUiController(customerService, trainerService,
        trainingSessionService, trainingServiceService, timeslotService);

    Trainer trainer = new Trainer();
    trainer.setId(3L);
    trainer.setName("Nina Patel");

    Timeslot timeslot = new Timeslot();
    timeslot.setId(9L);
    timeslot.setTrainer(trainer);
    timeslot.setIsAvailable(true);

    when(trainerService.findById(3L)).thenReturn(trainer);
    when(timeslotService.getAvailableTimeslotsByTrainerId(3L)).thenReturn(List.of(timeslot));

    ExtendedModelMap model = new ExtendedModelMap();
    MockHttpSession httpSession = new MockHttpSession();
    httpSession.setAttribute("customerId", 1L);
    String viewName = controller.bookSessionPage(3L, model, httpSession);

    assertEquals("customer/book-session", viewName);
    assertEquals(trainer, model.getAttribute("trainer"));
    assertEquals(List.of(timeslot), model.getAttribute("timeslots"));
  }
}
