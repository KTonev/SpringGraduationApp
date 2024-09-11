package com.project.graduation.services;

import com.project.graduation.data.dto.DefenseDto;
import com.project.graduation.data.entity.Student;
import com.project.graduation.data.entity.Teacher;

import java.util.Date;
import java.util.List;

public interface DefenseService {
    void saveDefense(DefenseDto defenseDto, List<Student> studentIds, List<Teacher> teacherIds);
}
