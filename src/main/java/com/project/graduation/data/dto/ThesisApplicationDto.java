package com.project.graduation.data.dto;

import com.project.graduation.data.entity.Student;
import com.project.graduation.data.entity.Teacher;
import lombok.Data;

@Data
public class ThesisApplicationDto {
    private int id;
    private String topic;
    private String purpose;
    private String tasks;
    private String technologies;
    private Student student;
    private Teacher supervisor;
    private Boolean isApproved;
}
