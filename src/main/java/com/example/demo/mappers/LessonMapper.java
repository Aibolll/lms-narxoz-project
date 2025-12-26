package com.example.demo.mappers;

import com.example.demo.dtos.LessonCreateDto;
import com.example.demo.dtos.LessonResponseDto;
import com.example.demo.models.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    Lesson toEntity(LessonCreateDto dto);

    @Mapping(source = "course.id", target = "courseId")
    LessonResponseDto toDto(Lesson lesson);
}