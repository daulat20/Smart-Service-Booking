package com.smart.service.booking.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.smart.service.booking.dto.ProviderResponseDTO;
import com.smart.service.booking.entity.Provider;
import com.smart.service.booking.repository.ProviderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderResponseDTO saveProvider(Provider provider) {
    	 Provider saved = providerRepository.save(provider);

    	    ProviderResponseDTO dto = new ProviderResponseDTO();
    	    dto.setId(saved.getId());
    	    dto.setCompanyName(saved.getCompanyName());
    	    dto.setDescription(saved.getDescription());

    	    dto.setUserId(saved.getUser().getId());

    	    return dto;    }

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    public Provider getProviderById(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found"));
    }
}
