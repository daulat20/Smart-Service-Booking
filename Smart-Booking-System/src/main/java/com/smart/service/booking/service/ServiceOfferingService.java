package com.smart.service.booking.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.smart.service.booking.dto.ServiceResponse;
import com.smart.service.booking.entity.ServiceOffering;
import com.smart.service.booking.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceOfferingService {

    private final ServiceRepository serviceRepository;

    public ServiceResponse addService(ServiceOffering service) {
        ServiceOffering saved = serviceRepository.save(service);

        ServiceResponse dto = new ServiceResponse();
        dto.setId(saved.getId());
        dto.setTitle(saved.getTitle());
        dto.setPrice(saved.getPrice());
        dto.setStatus(saved.getStatus().name());

        dto.setProviderId(saved.getProvider().getId());

        return dto;
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
