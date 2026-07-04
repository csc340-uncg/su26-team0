package com.csc340.fitmatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csc340.fitmatch.entity.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
  Trainer findByEmail(String email);

  List<Trainer> findBySpecialtiesContainingIgnoreCase(String specialty);

}
