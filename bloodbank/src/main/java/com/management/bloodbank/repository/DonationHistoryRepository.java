package com.management.bloodbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.bloodbank.model.DonationHistory;

public interface DonationHistoryRepository extends JpaRepository<DonationHistory, Long> {

	List<DonationHistory> findByUserId(Long userId);

	List<DonationHistory> findAll();
}
