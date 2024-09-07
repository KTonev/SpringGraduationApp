package com.project.graduation.services.implementations;

import com.project.graduation.data.dto.ThesisApplicationDto;
import com.project.graduation.data.entity.Student;
import com.project.graduation.data.entity.Teacher;
import com.project.graduation.data.entity.ThesisApplication;
import com.project.graduation.data.repository.StudentRepository;
import com.project.graduation.data.repository.TeacherRepository;
import com.project.graduation.data.repository.ThesisApplicationRepository;
import com.project.graduation.services.ThesisApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ThesisApplicationImpl implements ThesisApplicationService {
    private final ThesisApplicationRepository thesisApplicationRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public void saveThesisApplication(ThesisApplicationDto thesisApplicationDto, long studentId, long teacherId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        ThesisApplication thesisApplication = new ThesisApplication();
        thesisApplication.setTopic(thesisApplicationDto.getTopic());
        thesisApplication.setPurpose(thesisApplicationDto.getPurpose());
        thesisApplication.setTasks(thesisApplicationDto.getTasks());
        thesisApplication.setTechnologies(thesisApplicationDto.getTechnologies());
        thesisApplication.setStudent(student);
        thesisApplication.setSupervisor(teacher);
        thesisApplication.setApproved(false);

        thesisApplicationRepository.save(thesisApplication);
    }
}
