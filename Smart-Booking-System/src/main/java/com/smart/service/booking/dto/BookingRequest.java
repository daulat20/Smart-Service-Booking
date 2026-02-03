package com.smart.service.booking.dto;


import java.time.LocalDateTime;

import com.smart.service.booking.enums.BookingStatus;

import lombok.Data;

@Data
public class BookingRequest {

	 private Long bookingId;
	    private String userName;
	    private String serviceName;
	    private BookingStatus status;
	    private LocalDateTime bookingDate;
	    private Long userId;     
	    private Long serviceId;
}

