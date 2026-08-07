package com.management.bloodbank.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.Centers;
import com.management.bloodbank.model.Stock;
import com.management.bloodbank.repository.StockRepository;
import com.management.bloodbank.service.StockService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

	private final StockRepository stockRepository;

	@Override
	public List<Stock> findAll() {
		return stockRepository.findAll();
	}

	@Override
	public void addUnits(Centers centers, BloodGroup bloodGroup, int units) {
		Stock stock = stockRepository.findByCentersAndBloodGroup(centers, bloodGroup).orElseGet(() -> {
			Stock newStock = new Stock();
			newStock.setCenters(centers);
			newStock.setBloodGroup(bloodGroup);
			newStock.setQuantity(0);
			return newStock;
		});

		stock.setQuantity(stock.getQuantity() + units);
		stockRepository.save(stock);
	}
}