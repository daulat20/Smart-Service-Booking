package com.smart.service.booking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.smart.service.booking.dto.BookingRequest;
import com.smart.service.booking.entity.Booking;
import com.smart.service.booking.entity.ServiceOffering;
import com.smart.service.booking.entity.User;
import com.smart.service.booking.enums.BookingStatus;
import com.smart.service.booking.repository.BookingRepository;
import com.smart.service.booking.repository.ServiceRepository;
import com.smart.service.booking.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;

    /**
     * Creates a booking for the currently authenticated user.
     * The userId is resolved from the JWT token to prevent IDOR attacks —
     * clients cannot create bookings on behalf of another user.
     */
    public Booking createBooking(BookingRequest request) {
        // Resolve authenticated user from security context (prevents IDOR)
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        ServiceOffering service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        Booking booking = Booking.builder()
                .user(user)
                .service(service)
                .bookingDate(
                    request.getBookingDate() != null
                        ? request.getBookingDate()
                        : LocalDateTime.now()
                )
                .status(
                    request.getStatus() != null
                        ? request.getStatus()
                        : BookingStatus.PENDING
                )
                .build();

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    /**
     * Returns only the bookings belonging to the currently authenticated user.
     */
    public List<Booking> getMyBookings() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));
        return bookingRepository.findByUserId(user.getId());
    }
}
