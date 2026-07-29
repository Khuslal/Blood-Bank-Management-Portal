package com.management.bloodbank.service;

import com.management.bloodbank.model.Donor;

import java.util.List;
import java.util.Optional;

public interface DonorService {
    Donor registerDonor(Donor donor);
    Optional<Donor> findByUserEmail(String email);
    List<Donor> findAll();
}