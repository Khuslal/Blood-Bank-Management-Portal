package com.management.bloodbank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.User;
import com.management.bloodbank.model.UserRole;

public interface UserService {

	Optional<User> findByEmail(String email);
	List<User> findByBloodGroup(BloodGroup bloodGroup);
	List<User> findByFirstNameAndLastName(String firstName, String lastName);
	List<User> findByGender(String gender);
	List<User> findByPhone(String phone);
	List<User> findByDob(LocalDate dob);
	User registerNewUser(User user);
	List<User> findByRole(UserRole role);
    List<User> findByRoleAndBloodGroup(UserRole role, BloodGroup bloodGroup);
}
