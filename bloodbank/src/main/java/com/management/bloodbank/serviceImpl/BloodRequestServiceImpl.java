package com.management.bloodbank.serviceImpl;

import com.management.bloodbank.model.BloodRequest;
import com.management.bloodbank.model.RequestStatus;
import com.management.bloodbank.repository.BloodRequestRepository;
import com.management.bloodbank.service.BloodRequestService;
import com.management.bloodbank.service.BloodStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodRequestServiceImpl implements BloodRequestService {

    private final BloodRequestRepository bloodRequestRepository;
    private final BloodStockService bloodStockService;

    @Override
    public BloodRequest submitRequest(BloodRequest request) {
        request.setStatus(RequestStatus.PENDING);
        return bloodRequestRepository.save(request);
    }

    @Override
    public List<BloodRequest> findAll() {
        return bloodRequestRepository.findAllByOrderByRequestDateDesc();
    }

    @Override
    @Transactional
    public BloodRequest updateStatus(Long requestId, RequestStatus status) {
        BloodRequest request = bloodRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found with id: " + requestId));

        if (status == RequestStatus.APPROVED) {
            boolean deducted = bloodStockService.deductUnits(request.getBloodGroup(), request.getUnitsRequired());
            if (!deducted) {
                throw new IllegalStateException("Not enough stock available to approve this request");
            }
            request.setStatus(RequestStatus.FULFILLED);
        } else {
            request.setStatus(status);
        }
        return bloodRequestRepository.save(request);
    }
}