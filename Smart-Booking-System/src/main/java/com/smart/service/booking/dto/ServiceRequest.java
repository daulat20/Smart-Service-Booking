package com.smart.service.booking.dto;


import java.math.BigDecimal;

import lombok.Data;

@Data
public class ServiceRequest {
    private String title;
    private String description;
    private BigDecimal price;
    private String status;   
    private Long providerId;
}

