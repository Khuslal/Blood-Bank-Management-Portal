package com.management.bloodbank.service;
import java.util.List;
import java.util.Optional;
import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.BloodRequest;
import com.management.bloodbank.model.Centers;
import com.management.bloodbank.model.User;
public interface BloodRequestService {
	BloodRequest registerNewBloodRequest(BloodRequest bloodRequest);
	Optional<BloodRequest> findByPatientName(String patientName);
	Optional<BloodRequest> findByBloodGroup(BloodGroup bloodGroup);
	Optional<BloodRequest> findByHospitalName(String hospitalName);
	Optional<BloodRequest> findByContactNumber(String contactNumber);
	List<BloodRequest> findAll();
	List<BloodRequest> findByCenters(Centers centers);
	void approve(Long requestId, User actor);
	void reject(Long requestId, User actor);
	void fulfill(Long requestId, User actor);
}