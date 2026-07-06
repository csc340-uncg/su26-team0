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

import com.csc340.fitmatch.entity.Review;
import com.csc340.fitmatch.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

  private final ReviewService reviewService;

  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
    Review review = reviewService.getReviewById(id);
    if (review == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(review);
  }

  @GetMapping("/customer/{customerId}")
  public ResponseEntity<List<Review>> getReviewsByCustomerId(@PathVariable Long customerId) {
    List<Review> reviews = reviewService.getReviewsByCustomerId(customerId);
    return ResponseEntity.ok(reviews);
  }

  @GetMapping("/trainer/{trainerId}")
  public ResponseEntity<List<Review>> getReviewsByTrainerId(@PathVariable Long trainerId) {
    List<Review> reviews = reviewService.getReviewsByTrainerId(trainerId);
    return ResponseEntity.ok(reviews);
  }

  @PostMapping
  public ResponseEntity<Review> createReview(@RequestBody Review review) {
    Review createdReview = reviewService.createReview(review);
    return ResponseEntity.ok(createdReview);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
    Review updatedReview = reviewService.updateReview(id, review);
    if (updatedReview == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(updatedReview);
  }

}
