package com.management.bloodbank.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BloodRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    private Integer unitsRequired;

    private String hospitalName;

    private String contactNumber;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Centers centers;

    // File stored on local filesystem; only metadata kept in DB
    private String documentName;
    private String documentPath;
    private String documentType;
}