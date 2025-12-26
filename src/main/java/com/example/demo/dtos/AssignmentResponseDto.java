package com.example.demo.dtos;

import lombok.Data;

@Data
public class AssignmentResponseDto {
    private Long id;
    private String taskName;
    private Integer maxScore;
    private Long lessonId;
}