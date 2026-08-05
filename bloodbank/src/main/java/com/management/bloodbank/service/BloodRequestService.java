package com.management.bloodbank.service;

import java.util.Optional;

import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.BloodRequest;

public interface BloodRequestService {
	BloodRequest registerNewBloodRequest(BloodRequest bloodRequest);
	Optional<BloodRequest> findByPatientName(String patientName);
	Optional<BloodRequest> findByBloodGroup(BloodGroup bloodGroup);
	Optional<BloodRequest> findByHospitalName(String hospitalName);
	Optional<BloodRequest> findByContactNumber(String contactNumber);
}
