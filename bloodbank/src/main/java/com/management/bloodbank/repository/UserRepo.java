package com.management.bloodbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.bloodbank.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
}
