package com.example.demo.dtos;

import lombok.Data;

@Data
public class CourseCreateDto {
    private String name;
    private String description;
    private int price;
}