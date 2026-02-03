package com.smart.service.booking.dto;


import lombok.Data;

@Data
public class ServiceResponse {

    private Long id;
    private String name;
    private String description;
    private double price;
    private String providerName;
}

