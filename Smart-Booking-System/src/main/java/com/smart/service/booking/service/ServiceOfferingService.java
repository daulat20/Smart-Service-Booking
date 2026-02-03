package com.smart.service.booking.service;


import com.smart.service.booking.entity.ServiceOffering;
import com.smart.service.booking.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceOfferingService {

    private final ServiceRepository serviceRepository;

    public ServiceOffering addService(ServiceOffering service) {
        return serviceRepository.save(service);
    }

    public List<ServiceOffering> getAllServices() {
        return serviceRepository.findAll();
    }

    public ServiceOffering getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}
