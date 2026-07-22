package com.management.bloodbank.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BloodRequests {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int requestId;
	private String hospitalName;
	private String bloodGroup;
	private int unitsRequested;
	private String status;
	private LocalDate requestDate;
}
