package com.project.graduation.data.repository;

import com.project.graduation.data.entity.ThesisApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThesisApplicationRepository extends JpaRepository<ThesisApplication, Long> {
        List<ThesisApplication> findByIsApprovedFalse();
        List<ThesisApplication> findByIsApprovedTrue();
}
