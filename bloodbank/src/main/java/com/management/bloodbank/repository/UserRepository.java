package com.management.bloodbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.bloodbank.model.BloodGroup;
import com.management.bloodbank.model.User;
import com.management.bloodbank.model.UserRole;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;



public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);
	Optional<User> findByEmailAndPassword(String email, String password);
	List<User> findByBloodGroup(BloodGroup bloodGroup);
	List<User> findByFirstNameAndLastName(String firstName, String lastName);
	List<User> findByGender(String gender);
	List<User> findByPhone(String phone);
	List<User> findByDob(LocalDate dob);
	List<User> findByRole(UserRole role);
    List<User> findByRoleAndBloodGroup(UserRole role, BloodGroup bloodGroup);
}
