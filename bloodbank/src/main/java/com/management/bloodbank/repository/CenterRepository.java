package com.management.bloodbank.repository;

import com.management.bloodbank.model.Center;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterRepository extends JpaRepository<Center, Long> {
}