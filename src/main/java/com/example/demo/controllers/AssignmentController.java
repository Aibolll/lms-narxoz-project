package com.example.demo.controllers;

import com.example.demo.dtos.AssignmentCreateDto;
import com.example.demo.dtos.AssignmentResponseDto;
import com.example.demo.services.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<AssignmentResponseDto> createAssignment(@RequestBody AssignmentCreateDto dto) {
        return ResponseEntity.ok(assignmentService.createAssignment(dto));
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<List<AssignmentResponseDto>> getAssignmentsByLesson(@PathVariable Long lessonId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByLesson(lessonId));
    }
}