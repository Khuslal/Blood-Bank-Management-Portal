package com.management.bloodbank.service;

import com.management.bloodbank.model.BloodRequest;
import com.management.bloodbank.model.RequestStatus;

import java.util.List;

public interface BloodRequestService {
    BloodRequest submitRequest(BloodRequest request);
    List<BloodRequest> findByRequesterId(Long requesterId);
    List<BloodRequest> findAll();
    BloodRequest updateStatus(Long requestId, RequestStatus status);
}