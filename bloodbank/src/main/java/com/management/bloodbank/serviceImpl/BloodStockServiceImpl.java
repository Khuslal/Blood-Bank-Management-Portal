package com.management.bloodbank.serviceImpl;

import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.BloodStock;
import com.management.bloodbank.model.Center;
import com.management.bloodbank.repository.BloodStockRepository;
import com.management.bloodbank.service.BloodStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodStockServiceImpl implements BloodStockService {

    private final BloodStockRepository bloodStockRepository;

    @Override
    public List<BloodStock> findAll() {
        return bloodStockRepository.findAllByOrderByBloodGroupAsc();
    }

    @Override
    @Transactional
    public void addUnits(Center center, BloodGroup bloodGroup, int units) {
        BloodStock stock = bloodStockRepository.findByCenterAndBloodGroup(center, bloodGroup)
                .orElseGet(() -> {
                    BloodStock newStock = new BloodStock();
                    newStock.setCenter(center);
                    newStock.setBloodGroup(bloodGroup);
                    newStock.setUnitsAvailable(0);
                    return newStock;
                });
        stock.setUnitsAvailable(stock.getUnitsAvailable() + units);
        stock.setLastUpdated(LocalDateTime.now());
        bloodStockRepository.save(stock);
    }

    @Override
    @Transactional
    public boolean deductUnits(BloodGroup bloodGroup, int units) {
        List<BloodStock> stocks = bloodStockRepository.findByBloodGroup(bloodGroup);
        int total = stocks.stream().mapToInt(BloodStock::getUnitsAvailable).sum();
        if (total < units) {
            return false;
        }
        int remaining = units;
        for (BloodStock stock : stocks) {
            if (remaining <= 0) break;
            int deduction = Math.min(stock.getUnitsAvailable(), remaining);
            stock.setUnitsAvailable(stock.getUnitsAvailable() - deduction);
            stock.setLastUpdated(LocalDateTime.now());
            bloodStockRepository.save(stock);
            remaining -= deduction;
        }
        return true;
    }

    @Override
    public int totalUnitsFor(BloodGroup bloodGroup) {
        return bloodStockRepository.findByBloodGroup(bloodGroup).stream()
                .mapToInt(BloodStock::getUnitsAvailable)
                .sum();
    }
}