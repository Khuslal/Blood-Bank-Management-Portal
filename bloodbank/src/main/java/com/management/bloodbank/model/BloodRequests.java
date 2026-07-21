package com.management.bloodbank.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class BloodRequests {
	private int requestId;
	private String hospitalName;
	private String bloodGroup;
	private int unitsRequested;
	private String status;
	private LocalDate requestDate;
}
