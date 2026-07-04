package com.csc340.fitmatch.service;

import org.springframework.stereotype.Service;

import com.csc340.fitmatch.entity.Review;
import com.csc340.fitmatch.repository.ReviewRepository;

@Service
public class ReviewService {

  private final ReviewRepository reviewRepository;

  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  public Review createReview(Review review) {
    return reviewRepository.save(review);
  }

  public Review getReviewByCustomerAndSession(Long customerId, Long sessionId) {
    return reviewRepository.findByCustomerIdAndSessionId(customerId, sessionId);
  }

}
