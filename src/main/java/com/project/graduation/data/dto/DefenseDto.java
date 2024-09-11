package com.project.graduation.data.dto;

import com.project.graduation.data.entity.Student;
import com.project.graduation.data.entity.Teacher;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class DefenseDto {
    private long id;
    private LocalDate date;
    private List<Student> students;
    private List<Teacher> teachers;
}
