package com.smart.service.booking.repository;


import com.smart.service.booking.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProviderId(Long providerId);

    List<Review> findByUserId(Long userId);
}

