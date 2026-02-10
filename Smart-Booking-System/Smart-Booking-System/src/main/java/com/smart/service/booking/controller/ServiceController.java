package com.smart.service.booking.controller;

import com.smart.service.booking.dto.ServiceRequest;
import com.smart.service.booking.dto.ServiceResponse;
import com.smart.service.booking.entity.ServiceOffering;
import com.smart.service.booking.service.ServiceOfferingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Service Controller
 * - Add/delete services → PROVIDER or ADMIN
 * - View services      → any authenticated user
 */
@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceOfferingService serviceOfferingService;

    /** Add a new service — PROVIDER or ADMIN only */
    @PostMapping("/AddService")
    @PreAuthorize("hasAnyRole('PROVIDER','ADMIN')")
    public ResponseEntity<ServiceResponse> addService(@Valid @RequestBody ServiceRequest request) {
        return ResponseEntity.ok(serviceOfferingService.addService(request));
    }

    /** Get all services — any authenticated user */
    @GetMapping
    @PreAuthorize("hasAnyRole('USER','PROVIDER','ADMIN')")
    public List<ServiceOffering> getAllServices() {
        return serviceOfferingService.getAllServices();
    }

    /** Get service by ID — any authenticated user */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','PROVIDER','ADMIN')")
    public ServiceOffering getServiceById(@PathVariable Long id) {
        return serviceOfferingService.getServiceById(id);
    }

    /** Delete a service — PROVIDER or ADMIN only */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('PROVIDER','ADMIN')")
    public ResponseEntity<String> deleteService(@PathVariable Long id) {
        serviceOfferingService.deleteService(id);
        return ResponseEntity.ok("Service deleted successfully");
    }
}
