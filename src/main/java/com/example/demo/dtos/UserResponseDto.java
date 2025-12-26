package com.example.demo.dtos;

import lombok.Data;
import java.util.List;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String fullName;
    private List<String> roles;
}