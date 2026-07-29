package com.management.bloodbank.serviceImpl;

import com.management.bloodbank.model.Donor;
import com.management.bloodbank.repository.DonorRepository;
import com.management.bloodbank.service.DonorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DonorServiceImpl implements DonorService {

    private final DonorRepository donorRepository;

    @Override
    public Donor registerDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    @Override
    public Optional<Donor> findByUserEmail(String email) {
        return donorRepository.findByUserEmail(email);
    }

    @Override
    public List<Donor> findAll() {
        return donorRepository.findAll();
    }
}