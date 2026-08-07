package com.management.bloodbank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.BloodRequest;
import com.management.bloodbank.model.Centers;

public interface BloodRequestRepository extends JpaRepository<BloodRequest, Long> {
	Optional<BloodRequest> findByPatientName(String patientName);

	Optional<BloodRequest> findByBloodGroup(BloodGroup bloodGroup);

	Optional<BloodRequest> findByHospitalName(String hospitalName);

	Optional<BloodRequest> findByContactNumber(String contactNumber);

	List<BloodRequest> findByCenters(Centers centers);
}
