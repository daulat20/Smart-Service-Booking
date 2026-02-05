package com.smart.service.booking.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.service.booking.dto.ProviderResponseDTO;
import com.smart.service.booking.entity.Provider;
import com.smart.service.booking.service.ProviderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @PostMapping("/addprovider")
    public ResponseEntity<ProviderResponseDTO> addProvider(
            @RequestBody Provider provider) {

        return ResponseEntity.ok(providerService.saveProvider(provider));
    }

    @GetMapping("/getprovider")
    public List<Provider> getAllProviders() {
        return providerService.getAllProviders();
    }
}
