package com.project.graduation.data.repository;

import com.project.graduation.data.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
