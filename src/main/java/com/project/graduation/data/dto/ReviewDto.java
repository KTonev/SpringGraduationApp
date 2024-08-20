package com.project.graduation.data.dto;

import com.project.graduation.data.entity.Teacher;
import com.project.graduation.data.entity.Thesis;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewDto {
    private Long id;
    private LocalDate date;
    private String content;
    private Boolean isPositive;
    private Thesis thesis;
    private Teacher reviewer;
}
