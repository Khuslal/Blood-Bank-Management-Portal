package com.management.bloodbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.bloodbank.model.Appointment;
import com.management.bloodbank.model.AppointmentStatus;
import com.management.bloodbank.model.User;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findByUser(User user);

    boolean existsByUser(User user);

    long countByStatus(AppointmentStatus status);

}