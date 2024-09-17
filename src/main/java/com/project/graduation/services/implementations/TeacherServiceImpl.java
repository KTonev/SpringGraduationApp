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

    @Override
    public TeacherDto getTeacherById(long id) {
        return teacherRepository.findById(id)
                .map(this::mapToTeacherDto)
                .orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id:" + id));
    }

    @Override
    public List<Teacher> getAllTeachersByIds(List<Long> teacherIds) {
        return teacherRepository.findAllById(teacherIds);
    }

    @Override
    public void createTeacher(TeacherDto teacherDto) {
        Teacher teacher = mapToTeacher(teacherDto);
        teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacherById(long id) {
        teacherRepository.deleteById(id);
    }

    private Teacher mapToTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherDto.getId());
        teacher.setName(teacherDto.getName());
        teacher.setPosition(teacherDto.getPosition());
        return teacher;
    }

    private TeacherDto mapToTeacherDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setName(teacher.getName());
        teacherDto.setPosition(teacher.getPosition());
        return teacherDto;
    }
}
