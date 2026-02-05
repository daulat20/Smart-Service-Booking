package com.smart.service.booking.dto;


import java.math.BigDecimal;

import lombok.Data;

@Data
public class ServiceResponse {
	 private Long id;
	    private String title;
	    private BigDecimal price;
	    private String status;
	    private Long providerId;
}

