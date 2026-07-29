package com.management.bloodbank.service;

import com.management.bloodbank.model.Center;

import java.util.List;

public interface CenterService {
    List<Center> findAll();
    Center save(Center center);
    Center findById(Long id);
}