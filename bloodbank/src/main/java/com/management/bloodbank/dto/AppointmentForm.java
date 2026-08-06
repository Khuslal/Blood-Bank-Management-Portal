package com.management.bloodbank.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class AppointmentForm {
    private Long donationCenter;
    private LocalDate donationDate;
    private String timeSlot;
}