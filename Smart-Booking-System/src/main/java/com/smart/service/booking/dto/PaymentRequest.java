package com.smart.service.booking.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PaymentRequest {

    private Long bookingId;
    private BigDecimal amount;
}
