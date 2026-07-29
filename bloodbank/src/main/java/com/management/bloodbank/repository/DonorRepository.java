package com.management.bloodbank.repository;

import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    Optional<Donor> findByUserId(Long userId);
    Optional<Donor> findByUserEmail(String email);
    List<Donor> findByBloodGroup(BloodGroup bloodGroup);
}