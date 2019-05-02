package com.zeeshan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zeeshan.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findUserByName(String name);
	public User findUserById(int id);
	
	//public User isUserExists(int id);
}
