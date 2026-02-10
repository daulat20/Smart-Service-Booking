package com.smart.service.booking.controller;

import com.smart.service.booking.entity.User;
import com.smart.service.booking.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User Controller
 * - Get all users  → ADMIN only
 * - Get user by ID → USER (own profile), ADMIN
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /** Get all users — ADMIN only */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /** Get user by ID — USER or ADMIN */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
