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

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ThesisApplicationDto> getUnapprovedThesisApplications() {
        List<ThesisApplication> unapprovedThesisApplications = thesisApplicationRepository. findByIsApprovedFalse();
        return unapprovedThesisApplications.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ThesisApplicationDto> getApprovedThesisApplications() {
        List<ThesisApplication> unapprovedThesisApplications = thesisApplicationRepository. findByIsApprovedTrue();
        return unapprovedThesisApplications.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void approveThesisApplication(long id) {
        ThesisApplication thesisApplication = thesisApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Thesis Application not found"));
        thesisApplication.setApproved(true);
        thesisApplicationRepository.save(thesisApplication);
    }

    private ThesisApplicationDto convertToDto(ThesisApplication thesisApplication) {
        ThesisApplicationDto thesisApplicationDto = new ThesisApplicationDto();
        thesisApplicationDto.setId(thesisApplication.getId());
        thesisApplicationDto.setTopic(thesisApplication.getTopic());
        thesisApplicationDto.setPurpose(thesisApplication.getPurpose());
        thesisApplicationDto.setTasks(thesisApplication.getTasks());
        thesisApplicationDto.setTechnologies(thesisApplication.getTechnologies());
        thesisApplicationDto.setStudent(thesisApplication.getStudent());
        thesisApplicationDto.setSupervisor(thesisApplication.getSupervisor());
        thesisApplicationDto.setApproved(thesisApplication.isApproved());
        return thesisApplicationDto;
    }
}
