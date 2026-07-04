package com.csc340.fitmatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csc340.fitmatch.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
  List<Review> findByCustomerId(Long customerId);

  @Query(value= "SELECT r.* FROM reviews r WHERE r.customer_id = :customerId AND r.training_session_id = :sessionId", nativeQuery = true )
  Review findByCustomerIdAndSessionId(Long customerId, Long sessionId);

  @Query(value= "SELECT r.* FROM reviews r WHERE r.training_session_id = :trainingSessionId", nativeQuery = true )
  List<Review> findByTrainingSessionId(Long trainingSessionId);

}
