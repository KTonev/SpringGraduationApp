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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ThesisServiceImpl implements ThesisService {

    private StudentRepository studentRepository;
    private ThesisRepository thesisRepository;
    private FileService fileService;

    @Override
    public void saveThesis(ThesisDto thesisDto, long studentId, MultipartFile file) {
        Student student = studentRepository.findById(studentId)
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

    @Override
    public List<ThesisDto> getAllTheses() {
        List<Thesis> theses = thesisRepository.findAll();
        return theses.stream().map((this::mapToThesisDto)).collect(Collectors.toList());
    }

    private ThesisDto mapToThesisDto(Thesis thesis) {
        ThesisDto thesisDto = new ThesisDto();
        thesisDto.setId(thesis.getId());
        thesisDto.setTitle(thesis.getTitle());
        thesisDto.setContent(thesis.getContent());
        thesisDto.setUploadDate(thesis.getUploadDate());
        thesisDto.setStudent(thesis.getStudent());
        return thesisDto;
    }

    private String saveFile(MultipartFile file) {
        return "C:/Projects/SaveData";
    }
}
