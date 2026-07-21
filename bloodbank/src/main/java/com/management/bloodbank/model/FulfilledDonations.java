package com.management.bloodbank.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class FulfilledDonations {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int request_id;
	private int donor_id;
	private int units_donated;
	private LocalDateTime donation_date;
}
