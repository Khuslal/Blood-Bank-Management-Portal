package com.management.bloodbank.serviceImpl;

import com.management.bloodbank.model.Donor;
import com.management.bloodbank.model.DonationHistory;
import com.management.bloodbank.repository.DonationHistoryRepository;
import com.management.bloodbank.repository.DonorRepository;
import com.management.bloodbank.service.BloodStockService;
import com.management.bloodbank.service.DonationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationHistoryServiceImpl implements DonationHistoryService {

    private final DonationHistoryRepository donationHistoryRepository;
    private final DonorRepository donorRepository;
    private final BloodStockService bloodStockService;

    @Override
    @Transactional
    public DonationHistory recordDonation(DonationHistory donationHistory) {
        DonationHistory saved = donationHistoryRepository.save(donationHistory);

        Donor donor = donationHistory.getDonor();
        donor.setLastDonationDate(donationHistory.getDonationDate());
        donorRepository.save(donor);

        bloodStockService.addUnits(
                donationHistory.getCenter(),
                donationHistory.getBloodGroup(),
                donationHistory.getUnitsDonated()
        );

        return saved;
    }

    @Override
    public List<DonationHistory> findByDonorId(Long donorId) {
        return donationHistoryRepository.findByDonorIdOrderByDonationDateDesc(donorId);
    }

    @Override
    public List<DonationHistory> findAll() {
        return donationHistoryRepository.findAllByOrderByDonationDateDesc();
    }
}