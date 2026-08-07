package com.management.bloodbank.service;

import java.util.List;

import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.Centers;
import com.management.bloodbank.model.Stock;

public interface StockService {
	List<Stock> findAll();
	void addUnits(Centers center, BloodGroup bloodGroup, int units);
}