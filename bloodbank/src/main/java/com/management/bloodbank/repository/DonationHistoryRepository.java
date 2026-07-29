package com.management.bloodbank.repository;

import com.management.bloodbank.model.DonationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationHistoryRepository extends JpaRepository<DonationHistory, Long> {
    List<DonationHistory> findByDonorIdOrderByDonationDateDesc(Long donorId);
    List<DonationHistory> findAllByOrderByDonationDateDesc();
}