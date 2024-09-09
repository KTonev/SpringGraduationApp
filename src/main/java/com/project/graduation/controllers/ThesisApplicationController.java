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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class ThesisApplicationController {
    private TeacherService teacherService;
    private StudentService studentService;
    private ThesisApplicationService thesisApplicationService;

    //summit thesis application
    @GetMapping(value = "/submitThesisApplication")
    public String submitThesisApplicationForm(@RequestParam("teacherId") long teacherId, Model model) {
        TeacherDto teacher = teacherService.getTeacherById(teacherId);
        List<StudentDto> students = studentService.getAllStudents();

        model.addAttribute("teacher", teacher);
        model.addAttribute("thesisApplication", new ThesisApplicationDto());
        model.addAttribute("students", students);
        return "thesisTemplates/submitThesisApplication";
    }

    @PostMapping("/submitThesisApplication")
    public String submitThesisApplication(@RequestParam("teacherId") long teacherId,
                                          @ModelAttribute("thesisApplication") ThesisApplicationDto thesisApplicationDto,
                                          @RequestParam("studentId") long studentId) {

        thesisApplicationService.saveThesisApplication(thesisApplicationDto, studentId, teacherId);
        return "redirect:/menuTeacher?thesisApplicationSuccess";
    }

    //get all approved and unapproved thesis applications
    @GetMapping(value = "/approvedThesisApplications")
    public String getApprovedThesisApplications(Model model) {
        List<ThesisApplicationDto> approvedApplications = thesisApplicationService.getApprovedThesisApplications();
        model.addAttribute("approvedApplications", approvedApplications);
        return "thesisTemplates/approvedThesisApplications";
    }

    @GetMapping("/unapprovedThesisApplications")
    public String showUnapprovedThesisApplications(Model model) {
        List<ThesisApplicationDto> unapprovedApplications = thesisApplicationService.getUnapprovedThesisApplications();
        model.addAttribute("unapprovedApplications", unapprovedApplications);
        return "thesisTemplates/unapprovedThesisApplications";
    }

    //approve thesis application
    @PostMapping("/approveThesisApplication")
    public String approveThesisApplication(@RequestParam("id") long id) {
        thesisApplicationService.approveThesisApplication(id);
        return "redirect:/unapprovedThesisApplications?success";
    }
}
