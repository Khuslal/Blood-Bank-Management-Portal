package com.management.bloodbank.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Centers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;
    private String phone;
    private String address;
    private String email;

    @OneToMany(mappedBy = "centers", cascade = CascadeType.ALL)
    private List<Stock> stocks = new ArrayList<>();

    @OneToMany(mappedBy = "centers")
    private List<BloodRequest> bloodRequests = new ArrayList<>();
}