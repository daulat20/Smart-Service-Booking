package com.smart.service.booking.dto;

import java.time.LocalDateTime;

import com.smart.service.booking.enums.BookingStatus;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingRequest {

    /**
     * Optional: if omitted, defaults to the current timestamp in BookingService.
     * If provided, it must be in the present or future.
     */
    @FutureOrPresent(message = "Booking date must be in the present or future")
    private LocalDateTime bookingDate;

    /**
     * Optional: defaults to PENDING if not provided.
     */
    private BookingStatus status;

    @NotNull(message = "Service ID is required")
    private Long serviceId;
}
