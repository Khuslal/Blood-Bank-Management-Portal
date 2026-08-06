package com.management.bloodbank.service;

import java.time.LocalDate;
import java.util.List;
import com.management.bloodbank.model.Appointment;
import com.management.bloodbank.model.Centers;
import com.management.bloodbank.model.User;

public interface AppointmentService {
    Appointment bookAppointment(User user, Long centerId, LocalDate donationDate, String timeSlot);
    List<Appointment> findAll();
    List<Appointment> findByCenter(Centers center);
    List<Appointment> findByUser(User user);
    void accept(Long appointmentId, User actor);
    void reject(Long appointmentId, User actor);
}