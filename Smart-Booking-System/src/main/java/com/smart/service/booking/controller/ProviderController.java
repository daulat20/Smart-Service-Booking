package com.smart.service.booking.controller;


import com.smart.service.booking.entity.Provider;
import com.smart.service.booking.service.ProviderService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @PostMapping
    public Provider addProvider(@RequestBody Provider provider) {
        return providerService.saveProvider(provider);
    }

    @GetMapping
    public List<Provider> getAllProviders() {
        return providerService.getAllProviders();
    }
}
