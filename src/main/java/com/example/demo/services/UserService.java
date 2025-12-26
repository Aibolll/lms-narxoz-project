package com.example.demo.services;

import com.example.demo.dtos.UserCreateDto;
import com.example.demo.dtos.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserResponseDto createUser(UserCreateDto userDto);
    UserResponseDto getUserByEmail(String email);
    UserResponseDto updateProfile(String email, UserCreateDto updateInfo);
    void changePassword(String email, String oldPassword, String newPassword);
    void deleteUser(Long id);
    UserResponseDto createUserByAdmin(UserCreateDto userDto, String roleName);
    UserResponseDto getMyProfile();
}