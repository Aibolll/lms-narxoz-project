package com.example.demo.services;

import com.example.demo.dtos.LessonCreateDto;
import com.example.demo.dtos.LessonResponseDto;
import java.util.List;

public interface LessonService {
    LessonResponseDto createLesson(LessonCreateDto lessonDto);
    List<LessonResponseDto> getLessonsByCourseId(Long courseId);
    LessonResponseDto getLessonById(Long id);
}