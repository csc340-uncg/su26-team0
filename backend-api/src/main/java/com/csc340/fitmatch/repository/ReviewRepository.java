package com.csc340.fitmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csc340.fitmatch.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
  Review findByCustomerIdAndTrainerId(Long customerId, Long trainerId);
  Review findByCustomerIdAndSessionId(Long customerId, Long sessionId);

}
