package com.management.bloodbank.serviceImpl;

import com.management.bloodbank.repository.DonationHistoryRepository;
import java.util.List;

import org.springframework.stereotype.Service;

import com.management.bloodbank.model.DonationHistory;
import com.management.bloodbank.service.DonationHistoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonationHistoryServiceImpl implements DonationHistoryService {

	private final DonationHistoryRepository donationHistoryRepository;

	@Override
	public DonationHistory recordDonation(DonationHistory history) {
		
		return donationHistoryRepository.save(history);
	}

	@Override
	public List<DonationHistory> findByUserId(Long userId) {
		
		return donationHistoryRepository.findByUserId(userId);
	}

	@Override
	public List<DonationHistory> findAll() {
		
		return donationHistoryRepository.findAll();
	}
	
}
