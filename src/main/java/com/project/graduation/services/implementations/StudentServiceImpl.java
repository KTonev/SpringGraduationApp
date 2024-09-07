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
        return students.stream().map((this::mapToStudentDto)).collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentById(long id) {
        return studentRepository.findById(id)
                .map(this::mapToStudentDto)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
    }

    @Override
    public void createStudent(StudentDto studentDto) {
        Student student = mapToStudent(studentDto);
        studentRepository.save(student);
    }

    @Override
    public void editStudent(StudentDto studentDto, long id) {
        Student student = mapToStudent(studentDto);
        studentRepository.save(student);
    }

    private Student mapToStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setFacultyNumber(studentDto.getFacultyNumber());
        return student;
    }

    private StudentDto mapToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setFacultyNumber(student.getFacultyNumber());
        return studentDto;
    }
}
