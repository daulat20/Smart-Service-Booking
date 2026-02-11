package com.smart.service.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smart.service.booking.dto.ProviderRequestDTO;
import com.smart.service.booking.dto.ProviderResponseDTO;
import com.smart.service.booking.entity.Provider;
import com.smart.service.booking.entity.User;
import com.smart.service.booking.repository.ProviderRepository;
import com.smart.service.booking.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;

    public ProviderResponseDTO saveProvider(ProviderRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));

        Provider provider = Provider.builder()
                .companyName(request.getCompanyName())
                .description(request.getDescription())
                .user(user)
                .build();

        Provider saved = providerRepository.save(provider);

        ProviderResponseDTO dto = new ProviderResponseDTO();
        dto.setId(saved.getId());
        dto.setCompanyName(saved.getCompanyName());
        dto.setDescription(saved.getDescription());
        dto.setUserId(saved.getUser().getId());

        return dto;
    }

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    public Provider getProviderById(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found"));
    }
}
