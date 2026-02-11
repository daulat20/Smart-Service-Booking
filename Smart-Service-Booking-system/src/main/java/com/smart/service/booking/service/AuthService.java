package com.smart.service.booking.service;

import com.smart.service.booking.dto.AuthResponse;
import com.smart.service.booking.dto.LoginRequest;
import com.smart.service.booking.dto.RegisterRequest;
import com.smart.service.booking.entity.Role;
import com.smart.service.booking.entity.User;
import com.smart.service.booking.enums.RoleType;
import com.smart.service.booking.repository.RoleRepository;
import com.smart.service.booking.repository.UserRepository;
import com.smart.service.booking.security.CustomUserDetailsService;
import com.smart.service.booking.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;

    /**
     * Registers a new user with the role specified in the request (by roleId).
     * Admin accounts must be created via createAdmin() which enforces ROLE_ADMIN.
     */
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + request.getRoleId()));

        // Prevent self-assignment of ADMIN role via the public register endpoint
        if (role.getRoleType() == RoleType.ROLE_ADMIN) {
            throw new RuntimeException("Cannot self-register as ADMIN. Use the admin creation endpoint.");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(role)
                .build();

        userRepository.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponse("Registration successful", user.getEmail(), token);
    }

    /**
     * Creates an admin account. Only accessible via the /create-admin endpoint.
     * The role is always ROLE_ADMIN regardless of what is in the request.
     */
    public AuthResponse createAdmin(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role adminRole = roleRepository
                .findByRoleType(RoleType.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Admin role not found. Please create ROLE_ADMIN first."));

        User admin = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(adminRole)
                .build();

        userRepository.save(admin);

        UserDetails userDetails = userDetailsService.loadUserByUsername(admin.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponse("Admin created successfully", admin.getEmail(), token);
    }

    public AuthResponse login(LoginRequest request) {
        // Authenticate via Spring Security (throws exception if invalid)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponse("Login successful", request.getEmail(), token);
    }
}
