package com.smart.service.booking.controller;

import com.smart.service.booking.dto.PaymentRequest;
import com.smart.service.booking.entity.Payment;
import com.smart.service.booking.service.PaymentService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public Payment makePayment(@RequestBody PaymentRequest request) {
        return paymentService.makePayment(request);
    }
}
