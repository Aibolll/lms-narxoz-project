package com.example.demo.services;

import com.example.demo.dtos.SubmissionCreateDto;
import com.example.demo.dtos.SubmissionResponseDto;
import com.example.demo.mappers.SubmissionMapper;
import com.example.demo.models.Assignment;
import com.example.demo.models.Submission;
import com.example.demo.models.User;
import com.example.demo.repositories.AssignmentRepository;
import com.example.demo.repositories.SubmissionRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final SubmissionMapper submissionMapper;

    @Override
    public SubmissionResponseDto submitAssignment(SubmissionCreateDto submissionDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User student = userRepository.findByEmail(email);

        Assignment assignment = assignmentRepository.findById(submissionDto.getAssignmentId())
                .orElseThrow(() -> new RuntimeException("Assignment not found"));


        Submission submission = submissionMapper.toEntity(submissionDto);
        submission.setStudent(student);
        submission.setAssignment(assignment);
        submission.setGraded(false);

        return submissionMapper.toDto(submissionRepository.save(submission));
    }

    @Override
    public SubmissionResponseDto gradeSubmission(Long submissionId, Integer grade) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        submission.setGrade(grade);
        submission.setGraded(true);

        return submissionMapper.toDto(submissionRepository.save(submission));
    }

    @Override
    public List<SubmissionResponseDto> getSubmissionsByAssignment(Long assignmentId) {
        return submissionRepository.findAllByAssignmentId(assignmentId).stream()
                .map(submissionMapper::toDto)
                .collect(Collectors.toList());
    }
}
