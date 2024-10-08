package com.project.graduation.controllers;

import com.project.graduation.data.dto.StudentDto;
import com.project.graduation.data.dto.TeacherDto;
import com.project.graduation.data.dto.ThesisApplicationDto;
import com.project.graduation.services.StudentService;
import com.project.graduation.services.TeacherService;
import com.project.graduation.services.ThesisApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class TeacherController {
    private TeacherService teacherService;
    private StudentService studentService;
    private ThesisApplicationService thesisApplicationService;

    @ResponseBody
    @GetMapping(value = "/teachers")
    public List<TeacherDto> getTeachers() {
        return teacherService.getAllTeachers();
    }

    @ResponseBody
    @GetMapping(value = "/teachers/{id}")
    public TeacherDto getTeacher(@PathVariable("id") long id) {
        return teacherService.getTeacherById(id);
    }

    @GetMapping(value = "/registerTeacher")
    public String registrationForm(Model model) {
        model.addAttribute("teacher", new TeacherDto());
        return "teacherTemplates/registerTeacher";
    }

    @PostMapping("/registerTeacher")
    public String registerTeacher(@ModelAttribute("teacher") TeacherDto teacherDto) {
        teacherService.createTeacher(teacherDto);
        return "redirect:/registerTeacher?success";
    }

    @GetMapping(value = "/menuTeacher")
    public String menuForm(Model model) {
        List<TeacherDto> teachers = teacherService.getAllTeachers();
        model.addAttribute("teacher", teachers);
        return "teacherTemplates/menuTeacher";
    }

    @GetMapping(value = "/editTeacher/{id}")
    public String editForm(@PathVariable("id") long id, Model model) {
        List<TeacherDto> teachers = teacherService.getAllTeachers();
        TeacherDto teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "teacherTemplates/editTeacher";
    }

    @PostMapping("/editTeacher/{id}")
    public String editTeacher(@PathVariable("id") long id, @ModelAttribute("teacher") TeacherDto teacherDto) {
        teacherService.createTeacher(teacherDto);
        return "redirect:/editTeacher/{id}?success";
    }

    @PostMapping("/deleteTeacher/{id}")
    public String deleteTeacher(@PathVariable("id") long id) {
        teacherService.deleteTeacherById(id);
        return "redirect:/menuTeacher?success";
    }
}
