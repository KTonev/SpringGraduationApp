package com.project.graduation.data.entity;


import com.project.graduation.data.dto.TeacherDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Enumerated(EnumType.STRING)
    private TeacherDto.Position position;

    public enum Position {
        PROFESSOR,
        ASSISTANT_PROFESSOR
    }
}
