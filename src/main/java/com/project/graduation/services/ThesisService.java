package com.project.graduation.services;

import com.project.graduation.data.dto.ThesisDto;
import org.springframework.web.multipart.MultipartFile;

public interface ThesisService {

    void saveThesis(ThesisDto thesisDto, int studentId, MultipartFile file);
}
