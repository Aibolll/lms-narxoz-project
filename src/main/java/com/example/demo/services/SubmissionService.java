package com.example.demo.services;

import com.example.demo.dtos.SubmissionCreateDto;
import com.example.demo.dtos.SubmissionResponseDto;
import java.util.List;

public interface SubmissionService {
    SubmissionResponseDto submitAssignment(SubmissionCreateDto submissionDto);
    SubmissionResponseDto gradeSubmission(Long submissionId, Integer grade);
    List<SubmissionResponseDto> getSubmissionsByAssignment(Long assignmentId);
}