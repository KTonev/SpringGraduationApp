package com.project.graduation.services;

import com.project.graduation.data.dto.ThesisDto;
import com.project.graduation.data.entity.Thesis;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ThesisService {

    void saveThesis(ThesisDto thesisDto, long studentId, MultipartFile file);

    List<ThesisDto> getAllTheses();
}
