package com.project.graduation.services.implementations;

import com.project.graduation.data.dto.ThesisDto;
import com.project.graduation.data.entity.Student;
import com.project.graduation.data.entity.Thesis;
import com.project.graduation.data.repository.StudentRepository;
import com.project.graduation.data.repository.ThesisRepository;
import com.project.graduation.services.FileService;
import com.project.graduation.services.ThesisService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ThesisServiceImpl implements ThesisService {

    private StudentRepository studentRepository;
    private ThesisRepository thesisRepository;
    private FileService fileService;

    @Override
    public void saveThesis(ThesisDto thesisDto, int studentId, MultipartFile file) {
        Student student = studentRepository.findById((long) studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Thesis thesis = new Thesis();
        thesis.setTitle(thesisDto.getTitle());
        thesis.setContent(thesisDto.getContent());
        thesis.setUploadDate(LocalDate.now());
        thesis.setStudent(student);

        String filePath = fileService.saveFile(file);
        thesis.setFilePath(filePath);

        thesisRepository.save(thesis);
    }

    private String saveFile(MultipartFile file) {
        return "C:/Projects/SaveData";
    }
}
