package com.smart.service.booking.controller;

import com.smart.service.booking.entity.ServiceOffering;
import com.smart.service.booking.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceOfferingService serviceOfferingService;

    @PostMapping
    public ServiceOffering addService(@RequestBody ServiceOffering serviceOffering) {
        return serviceOfferingService.addService(serviceOffering);
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
