package com.management.bloodbank.service;

import com.management.bloodbank.model.Donor;

public interface DonorService {
	void donorSignup(Donor donor);
	Donor donorLogin(String un, String pwd);
}
