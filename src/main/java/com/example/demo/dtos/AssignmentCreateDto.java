package com.example.demo.dtos;

import lombok.Data;

@Data
public class AssignmentCreateDto {
    private String taskName;
    private Integer maxScore;
    private Long lessonId;
}