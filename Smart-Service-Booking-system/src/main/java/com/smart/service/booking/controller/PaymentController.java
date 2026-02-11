package com.smart.service.booking.controller;

import com.smart.service.booking.dto.PaymentRequest;
import com.smart.service.booking.entity.Payment;
import com.smart.service.booking.service.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Payment Controller
 * - Make payment → USER only
 */
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    /** Make a payment — USER only */
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Payment makePayment(@Valid @RequestBody PaymentRequest request) {
        return paymentService.makePayment(request);
    }
}
