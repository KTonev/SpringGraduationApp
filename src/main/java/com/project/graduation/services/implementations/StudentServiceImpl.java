package com.project.graduation.services.implementations;

import com.project.graduation.data.dto.StudentDto;
import com.project.graduation.data.entity.Student;
import com.project.graduation.data.repository.StudentRepository;
import com.project.graduation.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map((student -> mapToStudentDto(student))).collect(Collectors.toList());
    }

    private StudentDto mapToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setFacultyNumber(student.getFacultyNumber());
        return studentDto;
    }
}
