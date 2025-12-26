package com.example.demo.dtos;

import lombok.Data;

@Data
public class LessonCreateDto {
    private String title;
    private String content;
    private Long courseId;
}