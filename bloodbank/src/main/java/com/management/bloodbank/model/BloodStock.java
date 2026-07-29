package com.management.bloodbank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "blood_stock", uniqueConstraints = @UniqueConstraint(columnNames = {"center_id", "bloodGroup"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloodStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "center_id", nullable = false)
    private Center center;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodGroup bloodGroup;

    @Column(nullable = false)
    private Integer unitsAvailable = 0;

    private LocalDateTime lastUpdated = LocalDateTime.now();
}