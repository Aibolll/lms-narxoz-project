package com.example.demo.dtos;

import lombok.Data;

@Data
public class LessonResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long courseId;
}