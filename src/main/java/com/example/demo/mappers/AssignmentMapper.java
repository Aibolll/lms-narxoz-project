package com.example.demo.mappers;

import com.example.demo.dtos.AssignmentCreateDto;
import com.example.demo.dtos.AssignmentResponseDto;
import com.example.demo.models.Assignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssignmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lesson", ignore = true)
    Assignment toEntity(AssignmentCreateDto dto);

    @Mapping(source = "lesson.id", target = "lessonId")
    AssignmentResponseDto toDto(Assignment assignment);
}