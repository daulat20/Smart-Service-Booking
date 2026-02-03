package com.smart.service.booking.service;

import com.smart.service.booking.dto.PaymentRequest;
import com.smart.service.booking.entity.Booking;
import com.smart.service.booking.entity.Payment;
import com.smart.service.booking.enums.PaymentStatus;
import com.smart.service.booking.repository.BookingRepository;
import com.smart.service.booking.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public Payment makePayment(PaymentRequest request) {

        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Payment payment = Payment.builder()
                .booking(booking)
                .amount(request.getAmount())
                .status(PaymentStatus.SUCCESS)
                .build();

        return paymentRepository.save(payment);
    }
}
