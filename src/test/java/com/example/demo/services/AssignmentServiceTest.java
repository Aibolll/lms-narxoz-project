package com.example.demo.services;

import com.example.demo.dtos.AssignmentResponseDto;
import com.example.demo.mappers.AssignmentMapper;
import com.example.demo.models.Assignment;
import com.example.demo.repositories.AssignmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssignmentServiceTest {

    @Mock private AssignmentRepository assignmentRepository;
    @Mock private AssignmentMapper assignmentMapper;
    @InjectMocks private AssignmentServiceImpl assignmentService;

    @Test
    void getAssignmentsByLesson_Success() {
        Assignment assignment = new Assignment();
        AssignmentResponseDto dto = new AssignmentResponseDto();

        when(assignmentRepository.findAllByLessonId(1L)).thenReturn(Collections.singletonList(assignment));
        when(assignmentMapper.toDto(assignment)).thenReturn(dto);

        List<AssignmentResponseDto> result = assignmentService.getAssignmentsByLesson(1L);
        assertEquals(1, result.size());
    }
}