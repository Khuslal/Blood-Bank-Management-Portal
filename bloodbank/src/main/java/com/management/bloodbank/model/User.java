package com.management.bloodbank.model;

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
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String username;
	private String email;
	private String phone;
	private String city;
	
	@ManyToOne
	@JoinColumn(name="blood_stock_summary", referencedColumnName = "bloodGroup")
	private BloodStockSummary bloodStockSumm;
	private String password;
	private String role;
	private int points;
	private String last_donation_date;
	private int donation_count;

}
