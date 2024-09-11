package com.project.graduation.controllers;

import com.project.graduation.data.dto.DefenseDto;
import com.project.graduation.data.dto.StudentDto;
import com.project.graduation.data.dto.TeacherDto;
import com.project.graduation.data.entity.Student;
import com.project.graduation.data.entity.Teacher;
import com.project.graduation.services.DefenseService;
import com.project.graduation.services.StudentService;
import com.project.graduation.services.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/defense")
public class DefenseController {
    private DefenseService defenseService;
    private StudentService studentService;
    private TeacherService teacherService;

    @GetMapping("/create")
    public String createDefenseForm(Model model) {
        List<StudentDto> students = studentService.getAllStudents();
        List<TeacherDto> teachers = teacherService.getAllTeachers();

        model.addAttribute("students", students);
        model.addAttribute("teachers", teachers);
        return "defenseTemplates/createDefense";
    }

    @PostMapping("/create")
    public String createDefense(@ModelAttribute("defense") DefenseDto defenseDto,
                                @RequestParam("students") List<Long> studentIds,
                                @RequestParam("teachers") List<Long> teacherIds) {

        List<Student> selectedStudents = studentService.getAllStudentsByIds(studentIds);
        List<Teacher> selectedTeachers = teacherService.getAllTeachersByIds(teacherIds);

        defenseService.saveDefense(defenseDto, selectedStudents, selectedTeachers);

        return "redirect:/menuTeacher?success";
    }
}
