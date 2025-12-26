package com.example.demo.services;

import com.example.demo.dtos.UserCreateDto;
import com.example.demo.dtos.UserResponseDto;
import com.example.demo.mappers.UserMapper;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(UserCreateDto userDto) {
        User checkUser = userRepository.findByEmail(userDto.getEmail());
        if (checkUser != null) {
            throw new RuntimeException("User with this email already exists");
        }

        User user = userMapper.toEntity(userDto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            throw new RuntimeException("Role ROLE_USER not found in database");
        }
        user.setRoles(Collections.singletonList(userRole));

        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return userMapper.toDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
    @Override
    public UserResponseDto updateProfile(String email, UserCreateDto updateInfo) {
        User user = userRepository.findByEmail(email);
        user.setFullName(updateInfo.getFullName());
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void changePassword(String email, String oldPassword, String newPassword) {
        User user = userRepository.findByEmail(email);

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Wrong old password");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public UserResponseDto createUserByAdmin(UserCreateDto userDto, String roleName) {
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findByName(roleName);
        if (role == null) throw new RuntimeException("Role not found");

        user.setRoles(Collections.singletonList(role));
        return userMapper.toDto(userRepository.save(user));
    }
    @Override
    public UserResponseDto getMyProfile() {
        String email = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email);

        return userMapper.toDto(user);
    }
}