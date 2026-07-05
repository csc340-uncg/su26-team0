package com.csc340.fitmatch.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csc340.fitmatch.entity.Customer;
import com.csc340.fitmatch.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerApiController {

  private final CustomerService customerService;

  public CustomerApiController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
    return customerService.getCustomerById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
    Customer customer = customerService.findByEmail(email);
    if (customer != null) {
      return ResponseEntity.ok(customer);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping()
  public ResponseEntity<List<Customer>> getAllCustomers() {
    List<Customer> customers = customerService.getAllCustomers();
    if (customers.isEmpty()) {
      return ResponseEntity.ok(Collections.emptyList());
    }
    return ResponseEntity.ok(customers);
  }

  @PostMapping()
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
    Customer createdCustomer = customerService.createCustomer(customer);
    return ResponseEntity.created(null).body(createdCustomer);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
    try {
      Customer customer = customerService.updateCustomer(id, updatedCustomer);
      return ResponseEntity.ok(customer);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}/personal-info")
  public ResponseEntity<Customer> updatePersonalInfo(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
    try {
      Customer customer = customerService.updatePersonalInfo(id, updatedCustomer);
      return ResponseEntity.ok(customer);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}/fitness-info")
  public ResponseEntity<Customer> updateFitnessInfo(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
    try {
      Customer customer = customerService.updateFitnessInfo(id, updatedCustomer);
      return ResponseEntity.ok(customer);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomer(id);
    return ResponseEntity.noContent().build();
  }

}
