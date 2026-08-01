package com.management.bloodbank.repository;

import com.management.bloodbank.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodRequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByOrderByRequestDateDesc();
}