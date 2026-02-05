package com.smart.service.booking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingResponse {

	private Long bookingId;
	private LocalDateTime bookingDate;
	private String status;

	private Long userId;
	private String userName;

	private Long serviceId;
	private String serviceTitle;
	private BigDecimal servicePrice;

}
