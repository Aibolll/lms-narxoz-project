package com.example.demo.dtos;

import lombok.Data;

@Data
public class SubmissionResponseDto {
    private Long id;
    private String studentAnswer;
    private Integer grade;
    private boolean isGraded;
    private Long assignmentId;
    private Long studentId;
}