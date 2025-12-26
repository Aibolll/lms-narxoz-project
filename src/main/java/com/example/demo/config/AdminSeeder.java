package com.example.demo.config;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user = userRepository.findByEmail("sergeant@narxoz.kz");
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");

        if (user != null && adminRole != null) {
            // Принудительно ставим пароль "123"
            user.setPassword(passwordEncoder.encode("123"));
            user.setRoles(Collections.singletonList(adminRole));
            userRepository.save(user);

            System.out.println("---------------------------------------------");
            System.out.println("SUCCESS! ADMIN PASSWORD RESET TO: 123");
            System.out.println("---------------------------------------------");
        }
    }
}