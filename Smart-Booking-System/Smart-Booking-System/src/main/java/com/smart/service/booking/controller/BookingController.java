package com.smart.service.booking.controller;

import com.smart.service.booking.dto.BookingRequest;
import com.smart.service.booking.entity.Booking;
import com.smart.service.booking.service.BookingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Booking Controller
 * - Create booking  → USER or PROVIDER (books as themselves via JWT identity)
 * - View own bookings → USER
 * - View all bookings → ADMIN or PROVIDER
 */
@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    /** Create a booking — USER or PROVIDER; userId resolved from JWT (IDOR-safe) */
    @PostMapping("/uploadbooking")
    @PreAuthorize("hasAnyRole('USER','PROVIDER')")
    public Booking createBooking(@Valid @RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }

    /** View own bookings — any authenticated user */
    @GetMapping("/mybookings")
    @PreAuthorize("hasAnyRole('USER','PROVIDER','ADMIN')")
    public List<Booking> getMyBookings() {
        return bookingService.getMyBookings();
    }

    /** View all bookings — ADMIN or PROVIDER only */
    @GetMapping("/getbooking")
    @PreAuthorize("hasAnyRole('ADMIN','PROVIDER')")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
