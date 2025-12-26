package com.example.demo.dtos;

import lombok.Data;

@Data
public class SubmissionCreateDto {
    private String studentAnswer;
    private Long assignmentId;
}
