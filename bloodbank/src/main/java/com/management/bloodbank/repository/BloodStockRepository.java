package com.management.bloodbank.repository;

import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.BloodStock;
import com.management.bloodbank.model.Center;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BloodStockRepository extends JpaRepository<BloodStock, Long> {
    List<BloodStock> findAllByOrderByBloodGroupAsc();
    Optional<BloodStock> findByCenterAndBloodGroup(Center center, BloodGroup bloodGroup);
    List<BloodStock> findByBloodGroup(BloodGroup bloodGroup);
}