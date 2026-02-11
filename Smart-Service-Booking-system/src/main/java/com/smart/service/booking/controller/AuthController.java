package com.smart.service.booking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.service.booking.dto.AuthResponse;
import com.smart.service.booking.dto.LoginRequest;
import com.smart.service.booking.dto.RegisterRequest;
import com.smart.service.booking.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Auth Controller
 * All endpoints are public (no token required).
 * CORS is configured globally in WebConfig.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Register a new USER or PROVIDER.
     * Provide a valid roleId (for ROLE_USER or ROLE_PROVIDER).
     * ROLE_ADMIN cannot be self-assigned via this endpoint.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {
            AuthResponse response = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Create an Admin user. The role is always forced to ROLE_ADMIN.
     */
    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody RegisterRequest request) {
        try {
            AuthResponse response = authService.createAdmin(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            // Return 401 without leaking internal error details
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }
    }
}
