package com.management.bloodbank.serviceImpl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.BloodRequest;
import com.management.bloodbank.repository.BloodRequestRepository;
import com.management.bloodbank.service.BloodRequestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BloodRequestServiceImpl implements BloodRequestService {

	private final BloodRequestRepository bloodRequestRepository;

	@Override
	public Optional<BloodRequest> findByPatientName(String patientName) {

		return bloodRequestRepository.findByPatientName(patientName);
	}

	@Override
	public Optional<BloodRequest> findByBloodGroup(BloodGroup bloodGroup) {

		return bloodRequestRepository.findByBloodGroup(bloodGroup);
	}

	@Override
	public Optional<BloodRequest> findByHospitalName(String hospitalName) {

		return bloodRequestRepository.findByHospitalName(hospitalName);
	}

	@Override
	public Optional<BloodRequest> findByContactNumber(String contactNumber) {

		return bloodRequestRepository.findByContactNumber(contactNumber);
	}

}
