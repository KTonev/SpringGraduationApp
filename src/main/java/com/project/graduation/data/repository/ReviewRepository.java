package com.project.graduation.data.repository;

import com.project.graduation.data.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByThesisId(long thesisId);
}
