package com.csc340.fitmatch.service;

import java.util.List;

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

  public List<Review> getReviewsByCustomerId(Long customerId) {
    return reviewRepository.findByCustomerId(customerId);
  }

  public List<Review> getReviewsByTrainerId(Long trainerId) {
    return reviewRepository.findByTrainerId(trainerId);
  }

  public Review updateReview(Long reviewId, Review review) {
    Review existingReview = reviewRepository.findById(reviewId).orElse(null);
    if (existingReview != null) {
      if (review.getRating() != 0) {
        existingReview.setRating(review.getRating());
      }
      if (review.getComments() != null) {
        existingReview.setComments(review.getComments());
      }
      if (review.getReplyText() != null) {
        existingReview.setReplyText(review.getReplyText());
      }
      return reviewRepository.save(existingReview);
    }
    return null;
  }

  public Review getReviewById(Long id) {
    return reviewRepository.findById(id).orElse(null);
  }

}
