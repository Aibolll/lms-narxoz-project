package com.example.demo.mappers;

import com.example.demo.dtos.CourseCreateDto;
import com.example.demo.dtos.CourseResponseDto;
import com.example.demo.models.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CourseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    Course toEntity(CourseCreateDto dto);

    CourseResponseDto toDto(Course course);
}