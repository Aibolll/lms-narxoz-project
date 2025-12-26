package com.example.demo.services;

import com.example.demo.dtos.AssignmentCreateDto;
import com.example.demo.dtos.AssignmentResponseDto;
import java.util.List;

public interface AssignmentService {
    AssignmentResponseDto createAssignment(AssignmentCreateDto assignmentDto);
    List<AssignmentResponseDto> getAssignmentsByLesson(Long lessonId);
}