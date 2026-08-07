package com.management.bloodbank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.Centers;
import com.management.bloodbank.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	List<Stock> findAll();
	Optional<Stock> findByCentersAndBloodGroup(Centers centers, BloodGroup bloodGroup);
}