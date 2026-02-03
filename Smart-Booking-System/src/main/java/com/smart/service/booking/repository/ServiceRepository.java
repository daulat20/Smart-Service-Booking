package com.smart.service.booking.repository;


import com.smart.service.booking.entity.ServiceOffering;
import com.smart.service.booking.enums.ServiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceOffering, Long> {

    List<ServiceOffering> findByStatus(ServiceStatus status);

    List<ServiceOffering> findByProviderId(Long providerId);
}
