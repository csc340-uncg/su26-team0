package com.csc340.fitmatch.dto;

public record TrainerStatistics(
    Long totalTrainerCustomers,
    Long totalTrainerReviews,
    Long totalTrainerSessions,
    Double averageTrainerRating,
    Double totalTrainerRevenue) {
}
