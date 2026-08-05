package com.management.bloodbank.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String gender;
	private String phone;
	private String address;

	@Past(message = "Date of birth must be in the past")
	private LocalDate dob;

	@Enumerated(EnumType.STRING)
	private BloodGroup bloodGroup;

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role = UserRole.DONOR;

	// For center manager role
	@ManyToOne
	@JoinColumn(name = "assigned_center_id")
	private Centers assignedCenter; // The center this staff member works at
}
