package com.example.demo.services;

import com.example.demo.dtos.SubmissionResponseDto;
import com.example.demo.mappers.SubmissionMapper;
import com.example.demo.models.Submission;
import com.example.demo.repositories.SubmissionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubmissionServiceTest {

    @Mock private SubmissionRepository submissionRepository;
    @Mock private SubmissionMapper submissionMapper;
    @InjectMocks private SubmissionServiceImpl submissionService;

    @Test
    void gradeSubmission_Success() {
        Submission submission = new Submission();
        submission.setId(1L);
        submission.setGraded(false);

        SubmissionResponseDto dto = new SubmissionResponseDto();
        dto.setGrade(100);
        dto.setGraded(true);

        when(submissionRepository.findById(1L)).thenReturn(Optional.of(submission));
        when(submissionRepository.save(any(Submission.class))).thenReturn(submission);
        when(submissionMapper.toDto(submission)).thenReturn(dto);

        SubmissionResponseDto result = submissionService.gradeSubmission(1L, 100);

        assertTrue(result.isGraded());
        assertEquals(100, result.getGrade());
    }
}