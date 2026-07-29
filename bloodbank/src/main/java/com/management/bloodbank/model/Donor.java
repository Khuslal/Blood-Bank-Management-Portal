package com.management.bloodbank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "donors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodGroup bloodGroup;

    private LocalDate dateOfBirth;

    private Double weightKg;

    private String gender;

    private LocalDate lastDonationDate;

    @Column(nullable = false)
    private boolean eligible = true;

    public boolean isEligibleToDonate() {
        if (!eligible) return false;
        if (lastDonationDate == null) return true;
        return lastDonationDate.isBefore(LocalDate.now().minusDays(90));
    }
}