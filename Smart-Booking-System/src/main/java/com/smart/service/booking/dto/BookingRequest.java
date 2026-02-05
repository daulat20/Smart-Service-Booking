package com.smart.service.booking.dto;


import java.time.LocalDateTime;

import com.smart.service.booking.enums.BookingStatus;

import lombok.Data;

@Data
public class BookingRequest {

//	  private Long bookingId;
	    private LocalDateTime bookingDate;
	    private BookingStatus status;

	    private Long userId;
	    private String userName;

	    private Long serviceId;
	    private String serviceName;

//	    private Long paymentId;
//	    private String paymentStatus;
}

