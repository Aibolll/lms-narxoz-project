package com.example.demo.services;

import com.example.demo.dtos.CourseCreateDto;
import com.example.demo.dtos.CourseResponseDto;
import java.util.List;

public interface CourseService {
    CourseResponseDto createCourse(CourseCreateDto courseDto);
    List<CourseResponseDto> getAllCourses();
    CourseResponseDto getCourseById(Long id);
}