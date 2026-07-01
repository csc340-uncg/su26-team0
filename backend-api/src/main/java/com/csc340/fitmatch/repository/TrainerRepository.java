package com.csc340.fitmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csc340.fitmatch.entity.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
  Trainer findByUsername(String username);

  Trainer findByEmail(String email);

}
