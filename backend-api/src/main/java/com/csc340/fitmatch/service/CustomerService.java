package com.csc340.fitmatch.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.csc340.fitmatch.entity.Customer;
import com.csc340.fitmatch.entity.TrainingSession;
import com.csc340.fitmatch.repository.CustomerRepository;
import com.csc340.fitmatch.repository.ReviewRepository;
import com.csc340.fitmatch.repository.TrainingSessionRepository;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final TrainingSessionRepository trainingSessionRepository;
  private final ReviewRepository reviewRepository;

  public CustomerService(CustomerRepository customerRepository,
      TrainingSessionRepository trainingSessionRepository, ReviewRepository reviewRepository) {
    this.customerRepository = customerRepository;
    this.trainingSessionRepository = trainingSessionRepository;
    this.reviewRepository = reviewRepository;
  }

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Optional<Customer> getCustomerById(Long id) {
    return customerRepository.findById(id);
  }

  public Customer createCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  public Customer updateCustomer(Long id, Customer updatedCustomer) {
    Optional<Customer> existingCustomer = customerRepository.findById(id);
    if (existingCustomer.isPresent()) {
      Customer customer = existingCustomer.get();
      customer.setEmail(updatedCustomer.getEmail());
      customer.setPassword(updatedCustomer.getPassword());
      customer.setAccountStatus(updatedCustomer.getAccountStatus());
      return customerRepository.save(customer);
    } else {
      throw new RuntimeException("Customer not found with id: " + id);
    }
  }

  public Customer updatePersonalInfo(Long id, Customer updatedCustomer) {
    Optional<Customer> existingCustomer = customerRepository.findById(id);
    if (existingCustomer.isPresent()) {
      Customer customer = existingCustomer.get();
      if (updatedCustomer.getName() != null) {
        customer.setName(updatedCustomer.getName());
      }
      if (updatedCustomer.getPhoneNumber() != null) {
        customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
      }
      if (updatedCustomer.getEmail() != null) {
        customer.setEmail(updatedCustomer.getEmail());
      }
      return customerRepository.save(customer);
    } else {
      throw new RuntimeException("Customer not found with id: " + id);
    }
  }

  public Customer updateFitnessInfo(Long id, Customer updatedCustomer) {
    Optional<Customer> existingCustomer = customerRepository.findById(id);
    if (existingCustomer.isPresent()) {
      Customer customer = existingCustomer.get();
      if (updatedCustomer.getCurrentWeight() != null) {
        customer.setCurrentWeight(updatedCustomer.getCurrentWeight());
      }
      if (updatedCustomer.getGoalWeight() != null) {
        customer.setGoalWeight(updatedCustomer.getGoalWeight());
      }
      if (updatedCustomer.getFitnessLevel() != null) {
        customer.setFitnessLevel(updatedCustomer.getFitnessLevel());
      }
      if (updatedCustomer.getFitnessGoals() != null) {
        customer.setFitnessGoals(updatedCustomer.getFitnessGoals());
      }
      if (updatedCustomer.getInjuriesOrHealthConcerns() != null) {
        customer.setInjuriesOrHealthConcerns(updatedCustomer.getInjuriesOrHealthConcerns());
      }
      return customerRepository.save(customer);
    } else {
      throw new RuntimeException("Customer not found with id: " + id);
    }
  }

  public boolean hasDependentObjects(Long id) {
    return !trainingSessionRepository.findByCustomerId(id).isEmpty()
        || !reviewRepository.findByCustomerId(id).isEmpty();
  }

  public void deleteCustomer(Long id) {
    if (hasDependentObjects(id)) {
      reviewRepository.deleteAll(reviewRepository.findByCustomerId(id));
      trainingSessionRepository.deleteAll(trainingSessionRepository.findByCustomerId(id));
    }
    customerRepository.deleteById(id);
  }

  public List<Customer> getCustomersByTrainerId(Long trainerId) {
    return trainingSessionRepository.findByTrainingServiceTrainerId(trainerId).stream()
        .map(TrainingSession::getCustomer)
        .filter(Objects::nonNull)
        .distinct()
        .collect(Collectors.toList());
  }

  public Customer findByEmail(String email) {
    return customerRepository.findByEmail(email);
  }

}
