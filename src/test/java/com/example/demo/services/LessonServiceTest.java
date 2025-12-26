package com.example.demo.services;

import com.example.demo.dtos.LessonResponseDto;
import com.example.demo.mappers.LessonMapper;
import com.example.demo.models.Lesson;
import com.example.demo.repositories.LessonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LessonServiceTest {

    @Mock private LessonRepository lessonRepository;
    @Mock private LessonMapper lessonMapper;
    @InjectMocks private LessonServiceImpl lessonService;

    @Test
    void getLessonById_Success() {
        Lesson lesson = new Lesson();
        lesson.setId(1L);
        LessonResponseDto dto = new LessonResponseDto();
        dto.setId(1L);

        when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson));
        when(lessonMapper.toDto(lesson)).thenReturn(dto);

        LessonResponseDto result = lessonService.getLessonById(1L);
        assertEquals(1L, result.getId());
    }
}