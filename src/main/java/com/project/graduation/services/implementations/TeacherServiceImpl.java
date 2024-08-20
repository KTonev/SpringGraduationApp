package com.project.graduation.services.implementations;

import com.project.graduation.data.dto.TeacherDto;
import com.project.graduation.data.entity.Teacher;
import com.project.graduation.data.repository.TeacherRepository;
import com.project.graduation.services.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private TeacherRepository teacherRepository;

    @Override
    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream().map((this::mapToTeacherDto)).collect(Collectors.toList());
    }

    private TeacherDto mapToTeacherDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setName(teacher.getName());
        teacherDto.setPosition(teacher.getPosition());
        return teacherDto;
    }
}
