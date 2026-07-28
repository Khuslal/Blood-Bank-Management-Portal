package com.management.bloodbank.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.bloodbank.model.Donor;
import com.management.bloodbank.repository.DonorRepository;
import com.management.bloodbank.service.DonorService;

@Service
public class DonorServiceImpl implements DonorService {

	@Autowired
	private DonorRepository donorRepo;

	@Override
	public void donorSignup(Donor donor) {
		donorRepo.save(donor);
	}

	@Override
	public Donor donorLogin(String un, String pwd) {
		Donor donor = donorRepo.findByUsernameAndPassword(un, pwd);
		return donor;
	}

}
