package com.management.bloodbank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "donation_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne
//    @JoinColumn(name = "appointment_id")
//    private Appointment appointment;

    @Column(nullable = false)
    private Integer unitsDonated = 1;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodGroup bloodGroup;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "center_id", nullable = false)
    private Centers center;

    @Column(nullable = false)
    private LocalDate donationDate = LocalDate.now();
}