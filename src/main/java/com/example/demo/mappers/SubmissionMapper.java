package com.example.demo.mappers;

import com.example.demo.dtos.SubmissionCreateDto;
import com.example.demo.dtos.SubmissionResponseDto;
import com.example.demo.models.Submission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubmissionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "grade", ignore = true)
    @Mapping(target = "graded", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "assignment", ignore = true)
    Submission toEntity(SubmissionCreateDto dto);

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "assignment.id", target = "assignmentId")
    SubmissionResponseDto toDto(Submission submission);
}