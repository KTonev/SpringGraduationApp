package com.project.graduation.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
@Table(name = "thesis")
public class Thesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Lob
    private String content;

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate uploadDate;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @OneToOne
    @JoinColumn(name = "thesis_application_id", nullable = false)
    private ThesisApplication thesisApplication;
}
