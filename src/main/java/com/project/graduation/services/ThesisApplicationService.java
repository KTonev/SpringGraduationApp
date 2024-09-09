package com.project.graduation.services;

import com.project.graduation.data.dto.ThesisApplicationDto;

import java.util.List;

public interface ThesisApplicationService {
    void saveThesisApplication(ThesisApplicationDto thesisApplicationDto, long studentId, long teacherId);

    List<ThesisApplicationDto> getUnapprovedThesisApplications();

    List<ThesisApplicationDto> getApprovedThesisApplications();

    void approveThesisApplication(long id);
}
