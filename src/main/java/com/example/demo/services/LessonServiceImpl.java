package com.example.demo.services;

import com.example.demo.dtos.LessonCreateDto;
import com.example.demo.dtos.LessonResponseDto;
import com.example.demo.mappers.LessonMapper;
import com.example.demo.models.Course;
import com.example.demo.models.Lesson;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.repositories.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    private final LessonMapper lessonMapper;

    @Override
    public LessonResponseDto createLesson(LessonCreateDto lessonDto) {
        Course course = courseRepository.findById(lessonDto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Lesson lesson = lessonMapper.toEntity(lessonDto);

        lesson.setCourse(course);

        Lesson savedLesson = lessonRepository.save(lesson);

        return lessonMapper.toDto(savedLesson);
    }

    @Override
    public List<LessonResponseDto> getLessonsByCourseId(Long courseId) {
        return lessonRepository.findAllByCourseId(courseId).stream()
                .map(lessonMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LessonResponseDto getLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        return lessonMapper.toDto(lesson);
    }
}