package com.smart.service.booking.dto;

import java.time.LocalDateTime;

import com.smart.service.booking.enums.BookingStatus;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingRequest {

    @Future(message = "Booking date must be in the future")
    private LocalDateTime bookingDate;

    private BookingStatus status;

    @NotNull(message = "Service ID is required")
    private Long serviceId;
}
