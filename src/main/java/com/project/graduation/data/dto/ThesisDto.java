package com.project.graduation.data.dto;

import com.project.graduation.data.entity.Student;
import com.project.graduation.data.entity.ThesisApplication;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ThesisDto {
    private int id;
    private String title;
    private String content;
    private LocalDate uploadDate;
    private Student student;
    private ThesisApplication thesisApplication;
}
