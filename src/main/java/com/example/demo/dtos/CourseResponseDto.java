package com.example.demo.dtos;

import lombok.Data;

@Data
public class CourseResponseDto {
    private Long id;
    private String name;
    private String description;
    private int price;
    private UserResponseDto author;
}