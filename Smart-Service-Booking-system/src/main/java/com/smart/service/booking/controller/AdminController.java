package com.smart.service.booking.controller;

import com.smart.service.booking.entity.Booking;
import com.smart.service.booking.entity.User;
import com.smart.service.booking.service.BookingService;
import com.smart.service.booking.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Admin Controller — accessible ONLY by ADMIN role
 */
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")   // class-level: all methods require ADMIN
public class AdminController {

    private final UserService userService;
    private final BookingService bookingService;

    /** View all users — ADMIN only */
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /** Delete a user — ADMIN only */
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }

    /** View all bookings — ADMIN only */
    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
