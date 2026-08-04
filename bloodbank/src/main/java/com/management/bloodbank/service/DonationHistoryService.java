package com.management.bloodbank.service;

import java.util.List;

import com.management.bloodbank.model.DonationHistory;

public interface DonationHistoryService {
	DonationHistory recordDonation(DonationHistory history);

	List<DonationHistory> findByUserId(Long userId);

	List<DonationHistory> findAll();
}
