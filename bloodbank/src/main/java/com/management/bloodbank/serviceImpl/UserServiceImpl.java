package com.management.bloodbank.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.Centers;
import com.management.bloodbank.model.User;
import com.management.bloodbank.model.UserRole;
import com.management.bloodbank.repository.CentersRepository;
import com.management.bloodbank.repository.UserRepository;
import com.management.bloodbank.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final CentersRepository centersRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public Optional<User> findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findByBloodGroup(BloodGroup bloodGroup) {
		
		return userRepository.findByBloodGroup(bloodGroup);
	}

	@Override
	public List<User> findByFirstNameAndLastName(String firstName, String lastName) {
		
		return userRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	@Override
	public List<User> findByGender(String gender) {
		
		return userRepository.findByGender(gender);
	}

	@Override
	public List<User> findByPhone(String phone) {
		
		return userRepository.findByPhone(phone);
	}

	@Override
	public List<User> findByDob(LocalDate dob) {
		
		return userRepository.findByDob(dob);
	}

	@Override
	public User registerNewUser(User user) {
		
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new IllegalArgumentException("Email is already registered!");
		}
		
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		return userRepository.save(user);
	}

	@Override
	public List<User> findByRole(UserRole role) {
		
		return userRepository.findByRole(role);
	}

	@Override
	public List<User> findByRoleAndBloodGroup(UserRole role, BloodGroup bloodGroup) {
		
		return userRepository.findByRoleAndBloodGroup(role, bloodGroup);
	}

	@Override
	public List<User> findAll() {
	    return userRepository.findAll();
	}

	@Override
	public void assignCenterManager(Long userId, Long centerId) {
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new IllegalArgumentException("User not found"));
	    Centers center = centersRepository.findById(centerId)
	            .orElseThrow(() -> new IllegalArgumentException("Center not found"));
	    user.setRole(UserRole.CENTER_MANAGER);
	    user.setAssignedCenter(center);
	    userRepository.save(user);
	}

}
