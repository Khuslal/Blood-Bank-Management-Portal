package com.management.bloodbank.serviceImpl;

import com.management.bloodbank.model.Center;
import com.management.bloodbank.repository.CenterRepository;
import com.management.bloodbank.service.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;

    @Override
    public List<Center> findAll() {
        return centerRepository.findAll();
    }

    @Override
    public Center save(Center center) {
        return centerRepository.save(center);
    }

    @Override
    public Center findById(Long id) {
        return centerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Center not found with id: " + id));
    }
}