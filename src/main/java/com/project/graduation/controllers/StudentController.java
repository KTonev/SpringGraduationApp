package com.project.graduation.controllers;

import com.project.graduation.data.dto.StudentDto;
import com.project.graduation.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;

    @GetMapping(value = "/students")
    public List<StudentDto> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/students/{id}")
    public StudentDto getStudent(@PathVariable("id") int id) {
        return studentService.getAllStudents().get(id);
    }
}
