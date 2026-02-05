package com.smart.service.booking.service;

import java.time.LocalDateTime;
import java.util.List;

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

    public Booking createBooking(BookingRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

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
}
