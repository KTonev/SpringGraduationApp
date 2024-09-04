package com.project.graduation.controllers;

import com.project.graduation.data.dto.StudentDto;
import com.project.graduation.data.dto.ThesisDto;
import com.project.graduation.services.StudentService;
import com.project.graduation.services.ThesisService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;
    private ThesisService thesisService;

    @ResponseBody
    @GetMapping(value = "/students")
    public List<StudentDto> getStudents() {
        return studentService.getAllStudents();
    }

    @ResponseBody
    @GetMapping(value = "/students/{id}")
    public StudentDto getStudent(@PathVariable("id") int id) {
        return studentService.getAllStudents().get(id);
    }

    @GetMapping(value = "/register")
    public String registrationForm(Model model) {
        model.addAttribute("student", new StudentDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute("student") StudentDto studentDto) {
        studentService.createStudent(studentDto);
        return "redirect:/register?success";
    }

    @GetMapping(value = "/edit")
    public String editForm(Model model) {
        List<StudentDto> students = studentService.getAllStudents();
        model.addAttribute("student", students);
        return "edit";
    }

    @GetMapping(value = "/edit/{id}")
    public String editForm(@PathVariable("id") int id, Model model) {
        List<StudentDto> students = studentService.getAllStudents();
        model.addAttribute("student", students.get(id));
        return "editStudent";
    }

    @PostMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") int id, @ModelAttribute("student") StudentDto studentDto) {
        studentService.editStudent(studentDto, id);
        return "redirect:/edit?success";
    }

    @GetMapping("/submitThesis")
    public String showThesisForm(@RequestParam("studentId") int studentId, Model model) {
        StudentDto studentDto = studentService.getAllStudents().get(studentId);
        model.addAttribute("student", studentDto);
        model.addAttribute("thesis", new ThesisDto());
        System.out.println("Student ID: " + studentId);
        return "submitThesis";
    }

    @PostMapping("submitThesis")
    public String submitThesis(@RequestParam("studentId") int studentId,
                               @ModelAttribute("thesis") ThesisDto thesisDto,
                               @RequestParam("file")MultipartFile file) {
        thesisService.saveThesis(thesisDto, studentId, file);
        return "redirect:/edit?thesisSuccess";
    }

}
