package com.management.bloodbank.service;

import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.BloodStock;
import com.management.bloodbank.model.Center;

import java.util.List;

public interface BloodStockService {
    List<BloodStock> findAll();
    void addUnits(Center center, BloodGroup bloodGroup, int units);
    boolean deductUnits(BloodGroup bloodGroup, int units);
    int totalUnitsFor(BloodGroup bloodGroup);
    List<BloodStock> lowStock(int threshold);
}