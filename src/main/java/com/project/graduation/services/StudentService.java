package com.project.graduation.services;

import com.project.graduation.data.dto.StudentDto;
import com.project.graduation.data.entity.Student;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(long id);
    void createStudent(StudentDto studentDto);
    void editStudent(StudentDto studentDto, long id);
}
