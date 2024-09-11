package com.project.graduation.services;

import com.project.graduation.data.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    void createReview(ReviewDto reviewDto, long thesisId, long teacherId);

    List<ReviewDto> getReviewsByThesisId(long thesisId);
}
