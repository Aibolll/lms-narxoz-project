package com.example.demo.controllers;

import com.example.demo.dtos.LessonCreateDto;
import com.example.demo.dtos.LessonResponseDto;
import com.example.demo.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping
    public ResponseEntity<LessonResponseDto> createLesson(@RequestBody LessonCreateDto lessonDto) {
        return new ResponseEntity<>(lessonService.createLesson(lessonDto), HttpStatus.CREATED);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<LessonResponseDto>> getLessonsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(lessonService.getLessonsByCourseId(courseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonResponseDto> getLessonById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }
}