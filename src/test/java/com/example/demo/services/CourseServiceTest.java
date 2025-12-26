package com.example.demo.services;

import com.example.demo.dtos.CourseResponseDto;
import com.example.demo.mappers.CourseMapper;
import com.example.demo.models.Course;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void getAllCourses_ReturnsList() {
        Course course = new Course();
        course.setName("Java Basic");

        CourseResponseDto dto = new CourseResponseDto();
        dto.setName("Java Basic");

        when(courseRepository.findAll()).thenReturn(Collections.singletonList(course));
        when(courseMapper.toDto(course)).thenReturn(dto);

        List<CourseResponseDto> result = courseService.getAllCourses();

        assertEquals(1, result.size());
        assertEquals("Java Basic", result.get(0).getName());
    }

    @Test
    void getCourseById_Success() {
        Long courseId = 1L;
        Course course = new Course();
        course.setId(courseId);

        CourseResponseDto dto = new CourseResponseDto();
        dto.setId(courseId);

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(courseMapper.toDto(course)).thenReturn(dto);

        CourseResponseDto result = courseService.getCourseById(courseId);

        assertNotNull(result);
        assertEquals(courseId, result.getId());
    }
}