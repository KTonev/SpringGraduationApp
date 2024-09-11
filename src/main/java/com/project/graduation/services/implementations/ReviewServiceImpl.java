package com.project.graduation.services.implementations;

import com.project.graduation.data.dto.ReviewDto;
import com.project.graduation.data.entity.Review;
import com.project.graduation.data.entity.Teacher;
import com.project.graduation.data.entity.Thesis;
import com.project.graduation.data.repository.ReviewRepository;
import com.project.graduation.data.repository.TeacherRepository;
import com.project.graduation.data.repository.ThesisRepository;
import com.project.graduation.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private ThesisRepository thesisRepository;
    private TeacherRepository teacherRepository;

    @Override
    public void createReview(ReviewDto reviewDto, long thesisId, long teacherId) {

        Thesis thesis = thesisRepository.findById(thesisId)
                .orElseThrow(() -> new RuntimeException("Thesis not found"));

        Teacher reviewer = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Review review = new Review();
        review.setContent(reviewDto.getContent());
        review.setDate(LocalDate.now());
        review.setPositive(reviewDto.isPositive());
        review.setReviewer(reviewer);
        review.setThesis(thesis);

        reviewRepository.save(review);
    }

    @Override
    public List<ReviewDto> getReviewsByThesisId(long thesisId) {
        List<Review> reviews = reviewRepository.findByThesisId(thesisId);
        return reviews.stream()
                .map(this::mapToReviewDto)
                .collect(Collectors.toList());
    }

    private Review mapToReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setContent(reviewDto.getContent());
        review.setPositive(reviewDto.isPositive());
        review.setDate(reviewDto.getDate());
        review.setReviewer(reviewDto.getReviewer());
        review.setThesis(reviewDto.getThesis());
        return review;
    }

    private ReviewDto mapToReviewDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setContent(review.getContent());
        reviewDto.setPositive(review.isPositive());
        reviewDto.setDate(review.getDate());
        reviewDto.setReviewer(review.getReviewer());
        reviewDto.setThesis(review.getThesis());
        return reviewDto;
    }
}
