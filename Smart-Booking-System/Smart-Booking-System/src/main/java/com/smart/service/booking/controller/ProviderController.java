package com.smart.service.booking.controller;

import com.smart.service.booking.dto.ProviderResponseDTO;
import com.smart.service.booking.entity.Provider;
import com.smart.service.booking.service.ProviderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Provider Controller
 * - Add/manage providers → PROVIDER or ADMIN
 * - View providers → any authenticated user (USER, PROVIDER, ADMIN)
 */
@RestController
@RequestMapping("/api/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    /** Add a new provider — PROVIDER or ADMIN only */
    @PostMapping("/addprovider")
    @PreAuthorize("hasAnyRole('PROVIDER','ADMIN')")
    public ResponseEntity<ProviderResponseDTO> addProvider(@Valid @RequestBody Provider provider) {
        return ResponseEntity.ok(providerService.saveProvider(provider));
    }

    /** Get all providers — any authenticated user */
    @GetMapping("/getprovider")
    @PreAuthorize("hasAnyRole('USER','PROVIDER','ADMIN')")
    public List<Provider> getAllProviders() {
        return providerService.getAllProviders();
    }
}
