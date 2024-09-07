package com.project.graduation.services;

import com.project.graduation.data.dto.ThesisApplicationDto;

public interface ThesisApplicationService {
    void saveThesisApplication(ThesisApplicationDto thesisApplicationDto, long studentId, long teacherId);
}
