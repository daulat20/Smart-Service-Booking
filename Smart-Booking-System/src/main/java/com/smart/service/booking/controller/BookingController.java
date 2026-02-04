package com.smart.service.booking.controller;

import com.smart.service.booking.dto.BookingRequest;
import com.smart.service.booking.entity.Booking;
import com.smart.service.booking.service.BookingService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/uploadbooking")
    public Booking createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
    }

    @GetMapping("/getbooking")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
