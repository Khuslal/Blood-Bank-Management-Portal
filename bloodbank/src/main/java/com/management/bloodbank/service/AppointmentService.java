package com.management.bloodbank.service;

import java.util.List;
import java.util.Optional;

import com.management.bloodbank.model.Appointment;
import com.management.bloodbank.model.AppointmentStatus;
import com.management.bloodbank.model.User;

public interface AppointmentService {

    Appointment save(Appointment appointment);

    List<Appointment> findAll();

    Optional<Appointment> findById(Long id);

    Optional<Appointment> findByUser(User user);

    boolean existsByUser(User user);

    void deleteById(Long id);

    long countByStatus(AppointmentStatus status);

}