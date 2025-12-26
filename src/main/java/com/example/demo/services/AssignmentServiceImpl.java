package com.example.demo.services;

import com.example.demo.dtos.AssignmentCreateDto;
import com.example.demo.dtos.AssignmentResponseDto;
import com.example.demo.mappers.AssignmentMapper;
import com.example.demo.models.Assignment;
import com.example.demo.models.Lesson;
import com.example.demo.repositories.AssignmentRepository;
import com.example.demo.repositories.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final LessonRepository lessonRepository;
    private final AssignmentMapper assignmentMapper;

    @Override
    public AssignmentResponseDto createAssignment(AssignmentCreateDto assignmentDto) {
        Lesson lesson = lessonRepository.findById(assignmentDto.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        Assignment assignment = assignmentMapper.toEntity(assignmentDto);

        assignment.setLesson(lesson);

        Assignment savedAssignment = assignmentRepository.save(assignment);

        return assignmentMapper.toDto(savedAssignment);
    }

    @Override
    public List<AssignmentResponseDto> getAssignmentsByLesson(Long lessonId) {
        return assignmentRepository.findAllByLessonId(lessonId).stream()
                .map(assignmentMapper::toDto)
                .collect(Collectors.toList());
    }
}