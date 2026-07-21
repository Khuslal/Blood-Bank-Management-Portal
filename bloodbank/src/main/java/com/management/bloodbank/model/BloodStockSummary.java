package com.management.bloodbank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "blood_stock_summary")
public class BloodStockSummary {
    
    @Id
    private String bloodGroup;
    
    private int totalUnits;
}
