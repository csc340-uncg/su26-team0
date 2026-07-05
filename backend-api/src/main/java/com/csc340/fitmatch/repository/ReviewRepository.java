package com.csc340.fitmatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csc340.fitmatch.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
  List<Review> findByCustomerId(Long customerId);
  List<Review> findByTrainerId(Long trainerId);
}
