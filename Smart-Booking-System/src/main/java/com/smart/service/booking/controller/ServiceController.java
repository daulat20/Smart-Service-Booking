package com.smart.service.booking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.service.booking.dto.ServiceRequest;
import com.smart.service.booking.dto.ServiceResponse;
import com.smart.service.booking.entity.ServiceOffering;
import com.smart.service.booking.service.ServiceOfferingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceOfferingService serviceOfferingService;

    @PostMapping("/AddService")
    public ResponseEntity<ServiceResponse> addService(
            @RequestBody ServiceRequest request) {

        return ResponseEntity.ok(
                serviceOfferingService.addService(request)
        );
    }


    @GetMapping
    public List<ServiceOffering> getAllServices() {
        return serviceOfferingService.getAllServices();
    }

    @GetMapping("/{id}")
    public ServiceOffering getServiceById(@PathVariable Long id) {
        return serviceOfferingService.getServiceById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceOfferingService.deleteService(id);
    }
}
