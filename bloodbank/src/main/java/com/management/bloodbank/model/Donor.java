package com.management.bloodbank.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="donors")
public class Donor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fullname;
	private String email;
	private String phone;
	private String city;
	private String bloodGroup;
	
	@Column(unique = true)
	private String username;
	private String password;
	private String donationDate;
	
	@OneToMany(mappedBy = "donor", cascade = CascadeType.ALL)
	private List<DonationHistory> donationHistory;
}
