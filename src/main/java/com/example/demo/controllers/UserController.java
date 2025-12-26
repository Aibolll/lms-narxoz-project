package com.example.demo.controllers;

import com.example.demo.dtos.UserCreateDto;
import com.example.demo.dtos.UserResponseDto;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/profile")
    public ResponseEntity<UserResponseDto> updateProfile(Authentication authentication, @RequestBody UserCreateDto dto) {
        return ResponseEntity.ok(userService.updateProfile(authentication.getName(), dto));
    }

    @PostMapping("/password")
    public ResponseEntity<String> changePassword(Authentication authentication,
                                                 @RequestParam String oldPassword,
                                                 @RequestParam String newPassword) {
        userService.changePassword(authentication.getName(), oldPassword, newPassword);
        return ResponseEntity.ok("Password changed successfully");
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> createUserByAdmin(@RequestBody UserCreateDto dto,
                                                             @RequestParam String roleName) {
        return ResponseEntity.ok(userService.createUserByAdmin(dto, roleName));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }
    @GetMapping("/profile")
    public ResponseEntity<UserResponseDto> getProfile() {
        return ResponseEntity.ok(userService.getMyProfile());
    }
}
