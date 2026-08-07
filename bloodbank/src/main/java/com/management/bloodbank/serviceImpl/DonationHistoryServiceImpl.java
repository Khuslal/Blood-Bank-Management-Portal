package com.management.bloodbank.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.bloodbank.model.Appointment;
import com.management.bloodbank.model.AppointmentStatus;
import com.management.bloodbank.model.DonationHistory;
import com.management.bloodbank.model.User;
import com.management.bloodbank.model.UserRole;
import com.management.bloodbank.repository.AppointmentRepository;
import com.management.bloodbank.repository.DonationHistoryRepository;
import com.management.bloodbank.service.DonationHistoryService;
import com.management.bloodbank.service.StockService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonationHistoryServiceImpl implements DonationHistoryService {

	private final DonationHistoryRepository donationHistoryRepository;
	private final AppointmentRepository appointmentRepository;
	private final StockService stockService;

	@Override
	public DonationHistory recordDonation(DonationHistory history) {
		return donationHistoryRepository.save(history);
	}

	@Override
	@Transactional // Makes the sql codes automicity
	public DonationHistory recordDonationFromAppointment(Long appointmentId, Integer unitsDonated, User actor) {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

		if (appointment.getStatus() != AppointmentStatus.ACCEPTED) {
			throw new IllegalArgumentException("Only accepted appointments can be marked as donated");
		}

		boolean authorized = actor.getRole() == UserRole.ADMIN
				|| (actor.getRole() == UserRole.CENTER_MANAGER
					&& actor.getAssignedCenter() != null
					&& actor.getAssignedCenter().getId().equals(appointment.getCenter().getId()));

		if (!authorized) {
			throw new IllegalArgumentException("Not authorized to record this donation");
		}

		int units = unitsDonated != null ? unitsDonated : 1;

		DonationHistory history = new DonationHistory();
		history.setAppointment(appointment);
		history.setUser(appointment.getUser());
		history.setCenter(appointment.getCenter());
		history.setBloodGroup(appointment.getUser().getBloodGroup());
		history.setUnitsDonated(units);
		history.setDonationDate(appointment.getDonationDate());

		DonationHistory saved = donationHistoryRepository.save(history);

		appointment.setStatus(AppointmentStatus.DONATED);
		appointmentRepository.save(appointment);

		stockService.addUnits(appointment.getCenter(), appointment.getUser().getBloodGroup(), units);

		return saved;
	}

	@Override
	public List<DonationHistory> findByUserId(Long userId) {
		return donationHistoryRepository.findByUserId(userId);
	}

	@Override
	public List<DonationHistory> findByCenterId(Long centerId) {
		return donationHistoryRepository.findByCenterId(centerId);
	}

	@Override
	public List<DonationHistory> findAll() {
		return donationHistoryRepository.findAll();
	}
}