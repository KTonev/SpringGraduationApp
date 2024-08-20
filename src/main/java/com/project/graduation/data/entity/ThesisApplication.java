package com.project.graduation.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
@Table(name = "thesis_application")
public class ThesisApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String topic;
    private String purpose;
    private String tasks;
    private String technologies;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher supervisor;

    private Boolean isApproved;
}
