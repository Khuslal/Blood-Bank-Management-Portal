package com.management.bloodbank.serviceImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.BloodRequest;
import com.management.bloodbank.model.BloodRequestStatus;
import com.management.bloodbank.model.Centers;
import com.management.bloodbank.model.User;
import com.management.bloodbank.model.UserRole;
import com.management.bloodbank.repository.BloodRequestRepository;
import com.management.bloodbank.service.BloodRequestService;
import com.management.bloodbank.service.StockService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BloodRequestServiceImpl implements BloodRequestService {
	private final BloodRequestRepository bloodRequestRepository;
	private final StockService stockService;

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
	@Override
	public BloodRequest registerNewBloodRequest(BloodRequest bloodRequest) {
		return bloodRequestRepository.save(bloodRequest);
	}
	@Override
	public List<BloodRequest> findAll() {
		return bloodRequestRepository.findAll();
	}
	@Override
	public List<BloodRequest> findByCenters(Centers centers) {
		return bloodRequestRepository.findByCenters(centers);
	}

	@Override
	public void approve(Long requestId, User actor) {
		BloodRequest request = getAuthorized(requestId, actor);
		if (request.getStatus() != BloodRequestStatus.PENDING) {
			throw new IllegalArgumentException("Only pending requests can be approved");
		}
		request.setStatus(BloodRequestStatus.APPROVED);
		bloodRequestRepository.save(request);
	}

	@Override
	public void reject(Long requestId, User actor) {
		BloodRequest request = getAuthorized(requestId, actor);
		if (request.getStatus() != BloodRequestStatus.PENDING) {
			throw new IllegalArgumentException("Only pending requests can be rejected");
		}
		request.setStatus(BloodRequestStatus.REJECTED);
		bloodRequestRepository.save(request);
	}

	@Override
	@Transactional
	public void fulfill(Long requestId, User actor) {
		BloodRequest request = getAuthorized(requestId, actor);
		if (request.getStatus() != BloodRequestStatus.APPROVED) {
			throw new IllegalArgumentException("Only approved requests can be fulfilled");
		}
		if (request.getCenters() == null) {
			throw new IllegalArgumentException("This request has no center assigned; cannot fulfill from stock");
		}

		stockService.removeUnits(request.getCenters(), request.getBloodGroup(), request.getUnitsRequired());

		request.setStatus(BloodRequestStatus.FULFILLED);
		bloodRequestRepository.save(request);
	}

	private BloodRequest getAuthorized(Long requestId, User actor) {
		BloodRequest request = bloodRequestRepository.findById(requestId)
				.orElseThrow(() -> new IllegalArgumentException("Request not found"));

		if (actor.getRole() == UserRole.ADMIN) {
			return request;
		}

		if (actor.getRole() == UserRole.CENTER_MANAGER
				&& actor.getAssignedCenter() != null
				&& request.getCenters() != null
				&& actor.getAssignedCenter().getId().equals(request.getCenters().getId())) {
			return request;
		}

		throw new IllegalArgumentException("Not authorized to manage this request");
	}
}