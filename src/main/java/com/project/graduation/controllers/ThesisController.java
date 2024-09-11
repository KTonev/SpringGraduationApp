package com.project.graduation.controllers;

import com.project.graduation.data.dto.StudentDto;
import com.project.graduation.data.dto.ThesisDto;
import com.project.graduation.services.StudentService;
import com.project.graduation.services.TeacherService;
import com.project.graduation.services.ThesisApplicationService;
import com.project.graduation.services.ThesisService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@AllArgsConstructor
public class ThesisController {
    private TeacherService teacherService;
    private StudentService studentService;
    private ThesisApplicationService thesisApplicationService;
    private ThesisService thesisService;

    //submit thesis
    @GetMapping("/submitThesis")
    public String showThesisForm(@RequestParam("studentId") long studentId, Model model) {
        StudentDto studentDto = studentService.getStudentById(studentId);
        model.addAttribute("student", studentDto);
        model.addAttribute("thesis", new ThesisDto());
        System.out.println("Student ID: " + studentId);
        return "thesisTemplates/submitThesis";
    }

    @PostMapping("submitThesis")
    public String submitThesis(@RequestParam("studentId") long studentId,
                               @ModelAttribute("thesis") ThesisDto thesisDto,
                               @RequestParam("file") MultipartFile file) {
        thesisService.saveThesis(thesisDto, studentId, file);
        return "redirect:/menuStudent?thesisSuccess";
    }

    //get uploaded theses
    @GetMapping("/theses")
    public String getUploadedTheses(Model model, @RequestParam("teacherId") long teacherId) {
        List<ThesisDto> theses = thesisService.getAllTheses();
        model.addAttribute("theses", theses);
        model.addAttribute("teacherId", teacherId);
        return "thesisTemplates/thesisList";
    }

}
