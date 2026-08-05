package com.management.bloodbank.service;

import java.util.List;
import java.util.Optional;

import com.management.bloodbank.model.Centers;

import jakarta.validation.Valid;

public interface CentersService {
	// For Public Use
	List<Centers> findAll();
	Optional<Centers> findByCityIgnoreCase(String city);
	Optional<Centers> findByNameContainingIgnoreCase(String name);
	Optional<Centers> findByAddressIgnoreCase(String address);
	
	// For Admin and Center Manager Use Only
	Optional<Centers> findById(Long id);
	Optional<Centers> findByEmail(String email);
	boolean existsByEmail(String email);
	Centers registerCenters(Centers centers);
	Centers updateCenter(@Valid Centers center);
}
