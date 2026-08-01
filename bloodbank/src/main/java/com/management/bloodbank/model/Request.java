package com.management.bloodbank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "blood_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Patient name is required")
    private String patientName;

    @NotNull(message = "Blood group is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodGroup bloodGroup;

    @NotNull(message = "Units required")
    private Integer unitsRequired;

    private String hospitalName;

    @NotBlank(message = "Contact number is required")
    private String contactNumber;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    // Stored filenames — null means that document wasn't uploaded
    private String prescriptionFile;
    private String hospitalRequisitionFile;
    private String patientSampleFile;
    private String donorExchangeFile;

    @Column(length = 1000)
    private String supportingDetails;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status = RequestStatus.PENDING;

    private LocalDateTime requestDate = LocalDateTime.now();
}