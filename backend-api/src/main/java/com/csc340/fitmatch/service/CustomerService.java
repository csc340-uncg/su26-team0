package com.csc340.fitmatch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.csc340.fitmatch.entity.Customer;
import com.csc340.fitmatch.repository.CustomerRepository;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
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
      customer.setUsername(updatedCustomer.getUsername());
      customer.setEmail(updatedCustomer.getEmail());
      customer.setPassword(updatedCustomer.getPassword());
      customer.setAccountStatus(updatedCustomer.getAccountStatus());
      return customerRepository.save(customer);
    } else {
      throw new RuntimeException("Customer not found with id: " + id);
    }
  }

  public void deleteCustomer(Long id) {
    customerRepository.deleteById(id);
  }

  public Customer findByUsername(String username) {
    return customerRepository.findByUsername(username);
  }

}
