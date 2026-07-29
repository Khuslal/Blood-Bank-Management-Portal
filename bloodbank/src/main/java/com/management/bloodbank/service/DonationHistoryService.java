package com.management.bloodbank.service;

import com.management.bloodbank.model.DonationHistory;

import java.util.List;

public interface DonationHistoryService {
    DonationHistory recordDonation(DonationHistory donationHistory);
    List<DonationHistory> findByDonorId(Long donorId);
    List<DonationHistory> findAll();
}