package com.management.bloodbank.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.management.bloodbank.model.Appointment;
import com.management.bloodbank.model.Centers;
import com.management.bloodbank.model.User;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUser(User user);
    List<Appointment> findByCenter(Centers center);
}