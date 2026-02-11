package com.smart.service.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smart.service.booking.dto.ServiceRequest;
import com.smart.service.booking.dto.ServiceResponse;
import com.smart.service.booking.entity.Provider;
import com.smart.service.booking.entity.ServiceOffering;
import com.smart.service.booking.enums.ServiceStatus;
import com.smart.service.booking.repository.ProviderRepository;
import com.smart.service.booking.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceOfferingService {

	private final ServiceRepository serviceRepository;
	private final ProviderRepository providerRepository;

	public ServiceResponse addService(ServiceRequest request) {

		if (request.getProviderId() == null) {
			throw new RuntimeException("providerId is required");
		}

		Provider provider = providerRepository.findById(request.getProviderId())
				.orElseThrow(() -> new RuntimeException("Provider not found"));

		ServiceOffering service = ServiceOffering.builder().title(request.getTitle())
				.description(request.getDescription()).price(request.getPrice())
				.status(ServiceStatus.valueOf(request.getStatus())).provider(provider).build();

		ServiceOffering saved = serviceRepository.save(service);

		ServiceResponse dto = new ServiceResponse();
		dto.setId(saved.getId());
		dto.setTitle(saved.getTitle());
		dto.setPrice(saved.getPrice());
		dto.setStatus(saved.getStatus().name());
		dto.setProviderId(provider.getId());

		return dto;
	}

	public List<ServiceOffering> getAllServices() {
		return serviceRepository.findAll();
	}

	public ServiceOffering getServiceById(Long id) {
		return serviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Service not found"));
	}

	public void deleteService(Long id) {
		serviceRepository.deleteById(id);
	}
}
