package com.smart.service.booking.repository;


import com.smart.service.booking.entity.Booking;
import com.smart.service.booking.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);

    List<Booking> findByServiceId(Long serviceId);

    List<Booking> findByStatus(BookingStatus status);
}

