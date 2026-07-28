package com.management.bloodbank.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
public class DonationHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/* 
	 * Request Id refers to a specific Hospital where donor donate blood
	 * It must be declared as @ManyToOne annotation
	*/
	private int requestId;
	
	@ManyToOne
	@JoinColumn(name = "donor_id")
	private Donor donor;
	private int unitsDonated;
	private LocalDateTime donationDate;
}
