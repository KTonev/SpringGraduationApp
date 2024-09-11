package com.project.graduation.services.implementations;

import com.project.graduation.data.dto.DefenseDto;
import com.project.graduation.data.entity.Defense;
import com.project.graduation.data.entity.Student;
import com.project.graduation.data.entity.Teacher;
import com.project.graduation.data.repository.DefenseRepository;
import com.project.graduation.services.DefenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class DefenseServiceImpl implements DefenseService {
    private DefenseRepository defenseRepository;

    @Override
    public void saveDefense(DefenseDto defenseDto, List<Student> selectedStudents, List<Teacher> selectedTeachers) {
        defenseDto.setStudents(selectedStudents);
        defenseDto.setTeachers(selectedTeachers);
        
        Defense defense = mapToDefense(defenseDto);

        defenseRepository.save(defense);
    }

    private Defense mapToDefense(DefenseDto defenseDto) {
        Defense defense = new Defense();
        defense.setDate(defenseDto.getDate());
        defense.setStudents(defenseDto.getStudents());
        defense.setTeachers(defenseDto.getTeachers());
        return defense;
    }
}
