package com.project.graduation.services;

import com.project.graduation.data.dto.TeacherDto;
import com.project.graduation.data.entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> getAllTeachers();

    TeacherDto getTeacherById(long id);

    List<Teacher> getAllTeachersByIds(List<Long> teacherIds);

    void createTeacher(TeacherDto teacherDto);

    void deleteTeacherById(long id);
}
