package com.management.bloodbank.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.management.bloodbank.model.Appointment;
import com.management.bloodbank.model.AppointmentStatus;
import com.management.bloodbank.model.User;
import com.management.bloodbank.repository.AppointmentRepository;
import com.management.bloodbank.service.AppointmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public Optional<Appointment> findByUser(User user) {
        return appointmentRepository.findByUser(user);
    }

    @Override
    public boolean existsByUser(User user) {
        return appointmentRepository.existsByUser(user);
    }

    @Override
    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public long countByStatus(AppointmentStatus status) {
        return appointmentRepository.countByStatus(status);
    }

}