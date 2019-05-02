package com.zeeshan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeeshan.dao.UserRepository;
import com.zeeshan.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findById(int id) {

		return userRepository.findUserById(id);
	}

	@Override
	public List<User> findAllUsers() {

		return userRepository.findAll();
	}

	@Override
	public User findByName(String name) {

		return userRepository.findUserByName(name);
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);

	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);

	}

	@Override
	public void deleteUserById(int id) {
		userRepository.deleteById(id);

	}

	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();

	}

	@Override
	public boolean isUserExists(int id) {
		User user = userRepository.findUserById(id);
		System.out.println(user);
		boolean status = true;
		if (user == null || user.equals("")) {
			status = false;
		}
		return status;
	}

}
