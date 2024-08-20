package com.project.graduation.data.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class TeacherDto {
    private int id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Position position;

    public enum Position {
        PROFESSOR,
        ASSISTANT_PROFESSOR
    }
}
