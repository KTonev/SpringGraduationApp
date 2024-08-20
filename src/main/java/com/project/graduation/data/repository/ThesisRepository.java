package com.project.graduation.data.repository;

import com.project.graduation.data.entity.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisRepository extends JpaRepository<Thesis, Long> {
}
