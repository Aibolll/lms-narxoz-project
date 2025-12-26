package com.example.demo.services;

import com.example.demo.dtos.UserCreateDto;
import com.example.demo.dtos.UserResponseDto;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUser_Success() {
        UserCreateDto dto = new UserCreateDto();
        dto.setEmail("test@test.com");
        dto.setPassword("123");

        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("123");

        Role role = new Role();
        role.setName("ROLE_USER");

        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setEmail("test@test.com");

        when(userRepository.findByEmail(dto.getEmail())).thenReturn(null);
        when(userMapper.toEntity(dto)).thenReturn(user);
        when(roleRepository.findByName("ROLE_USER")).thenReturn(role);
        when(passwordEncoder.encode(dto.getPassword())).thenReturn("encodedPass");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(responseDto);

        UserResponseDto result = userService.createUser(dto);

        assertNotNull(result);
        assertEquals("test@test.com", result.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUser_EmailAlreadyExists_ThrowsException() {
        UserCreateDto dto = new UserCreateDto();
        dto.setEmail("exist@test.com");

        when(userRepository.findByEmail(dto.getEmail())).thenReturn(new User());

        assertThrows(RuntimeException.class, () -> userService.createUser(dto));

        verify(userRepository, never()).save(any());
    }
}