package com.management.bloodbank.service;

import java.util.List;

import com.management.bloodbank.model.DonationHistory;
import com.management.bloodbank.model.User;

public interface DonationHistoryService {

	DonationHistory recordDonation(DonationHistory history);

	DonationHistory recordDonationFromAppointment(Long appointmentId, Integer unitsDonated, User actor);

	List<DonationHistory> findByUserId(Long userId);

	List<DonationHistory> findByCenterId(Long centerId);

	List<DonationHistory> findAll();
}
