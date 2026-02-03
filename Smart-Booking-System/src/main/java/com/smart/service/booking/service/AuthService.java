package com.smart.service.booking.service;


import org.springframework.stereotype.Service;

import com.smart.service.booking.dto.AuthResponse;
import com.smart.service.booking.dto.LoginRequest;
import com.smart.service.booking.dto.RegisterRequest;
import com.smart.service.booking.entity.Role;
import com.smart.service.booking.entity.User;
import com.smart.service.booking.repository.RoleRepository;
import com.smart.service.booking.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // encode later
        user.setPhone(request.getPhone());
        user.setRole(role);

        userRepository.save(user);

        return new AuthResponse("Registration successful", user.getEmail());
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid password");
//        }

        return new AuthResponse("Login successful", user.getEmail());
    }
}
