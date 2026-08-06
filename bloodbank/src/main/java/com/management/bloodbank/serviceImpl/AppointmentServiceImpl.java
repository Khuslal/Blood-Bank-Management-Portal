package com.management.bloodbank.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.management.bloodbank.model.Appointment;
import com.management.bloodbank.model.AppointmentStatus;
import com.management.bloodbank.model.Centers;
import com.management.bloodbank.model.User;
import com.management.bloodbank.model.UserRole;
import com.management.bloodbank.repository.AppointmentRepository;
import com.management.bloodbank.repository.CentersRepository;
import com.management.bloodbank.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

	private final AppointmentRepository appointmentRepository;
	private final CentersRepository centersRepository;

	@Override
	public Appointment bookAppointment(User user, Long centerId, LocalDate donationDate, String timeSlot) {
		Centers center = centersRepository.findById(centerId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid center selected"));

		Appointment appointment = new Appointment();
		appointment.setUser(user);
		appointment.setCenter(center);
		appointment.setDonationDate(donationDate);
		appointment.setTimeSlot(timeSlot);
		appointment.setStatus(AppointmentStatus.PENDING);

		return appointmentRepository.save(appointment);
	}

	@Override
	public List<Appointment> findAll() {
		return appointmentRepository.findAll();
	}

	@Override
	public List<Appointment> findByCenter(Centers center) {
		return appointmentRepository.findByCenter(center);
	}

	@Override
	public List<Appointment> findByUser(User user) {
		return appointmentRepository.findByUser(user);
	}

	@Override
	public void accept(Long appointmentId, User actor) {
		Appointment appointment = getAuthorized(appointmentId, actor);
		appointment.setStatus(AppointmentStatus.ACCEPTED);
		appointmentRepository.save(appointment);
	}

	@Override
	public void reject(Long appointmentId, User actor) {
		Appointment appointment = getAuthorized(appointmentId, actor);
		appointment.setStatus(AppointmentStatus.REJECTED);
		appointmentRepository.save(appointment);
	}

	private Appointment getAuthorized(Long appointmentId, User actor) {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

		if (actor.getRole() == UserRole.ADMIN) {
			return appointment;
		}

		if (actor.getRole() == UserRole.CENTER_MANAGER && actor.getAssignedCenter() != null
				&& actor.getAssignedCenter().getId().equals(appointment.getCenter().getId())) {
			return appointment;
		}

		throw new IllegalArgumentException("Not authorized to manage this appointment");
	}

}