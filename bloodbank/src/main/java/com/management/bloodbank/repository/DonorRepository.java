package com.management.bloodbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.bloodbank.model.Donor;

public interface DonorRepository extends JpaRepository<Donor, Integer> {
	Donor findByUsernameAndPassword(String username, String password);
}
