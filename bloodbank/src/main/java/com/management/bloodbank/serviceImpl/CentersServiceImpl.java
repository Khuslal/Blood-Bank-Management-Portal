package com.management.bloodbank.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.management.bloodbank.model.Centers;
import com.management.bloodbank.repository.CentersRepository;
import com.management.bloodbank.service.CentersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class CentersServiceImpl implements CentersService {

	private final CentersRepository centersRepository;

	@Override
	public List<Centers> findAll() {

		return centersRepository.findAll();
	}

	@Override
	public Optional<Centers> findByCityIgnoreCase(String city) {

		return centersRepository.findByCityIgnoreCase(city);
	}

	@Override
	public Optional<Centers> findByNameContainingIgnoreCase(String name) {

		return centersRepository.findByNameContainingIgnoreCase(name);
	}

	@Override
	public Optional<Centers> findByAddressIgnoreCase(String address) {

		return centersRepository.findByAddressIgnoreCase(address);
	}

	@Override
	public Optional<Centers> findById(Long id) {

		return centersRepository.findById(id);
	}

	@Override
	public Optional<Centers> findByEmail(String email) {

		return centersRepository.findByEmail(email);
	}

	@Override
	public boolean existsByEmail(String email) {

		return centersRepository.existsByEmail(email);
	}

	@Override
	public Centers registerCenters(Centers centers) {

		return centersRepository.save(centers);
	}

	@Override
	public Centers updateCenter(@Valid Centers center) {

		return centersRepository.save(center);
	}

}
