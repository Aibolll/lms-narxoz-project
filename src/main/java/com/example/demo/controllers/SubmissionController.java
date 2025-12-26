package com.example.demo.controllers;

import com.example.demo.dtos.SubmissionCreateDto;
import com.example.demo.dtos.SubmissionResponseDto;
import com.example.demo.services.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;
    @PostMapping
    public ResponseEntity<SubmissionResponseDto> submit(@RequestBody SubmissionCreateDto dto) {
        return ResponseEntity.ok(submissionService.submitAssignment(dto));
    }

    @PostMapping("/{id}/grade")
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    public ResponseEntity<SubmissionResponseDto> grade(@PathVariable Long id, @RequestParam Integer grade) {
        return ResponseEntity.ok(submissionService.gradeSubmission(id, grade));
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<SubmissionResponseDto>> getByAssignment(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(submissionService.getSubmissionsByAssignment(assignmentId));
    }
}