package com.management.bloodbank.service;

import com.management.bloodbank.model.Request;
import com.management.bloodbank.model.RequestStatus;

import java.util.List;

public interface BloodRequestService {
    Request submitRequest(Request request);
    List<Request> findAll();
    Request updateStatus(Long requestId, RequestStatus status);
}