package com.project.graduation.controllers;

import com.project.graduation.data.dto.TeacherDto;
import com.project.graduation.services.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TeacherController {
    private TeacherService teacherService;

    @GetMapping(value = "/teachers")
    public List<TeacherDto> getTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping(value = "/teachers/{id}")
    public TeacherDto getTeacher(@PathVariable("id") int id) {
        return teacherService.getAllTeachers().get(id);
    }
}
