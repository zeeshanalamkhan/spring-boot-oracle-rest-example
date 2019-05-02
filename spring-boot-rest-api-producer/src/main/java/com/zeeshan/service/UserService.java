package com.zeeshan.service;

import java.util.List;

import com.zeeshan.model.User;

public interface UserService {

	public User findById(int id);

	public List<User> findAllUsers();

	public User findByName(String name);

	public void saveUser(User user);

	public void updateUser(User user);

	public void deleteUserById(int id);

	public void deleteAllUsers();

	public boolean isUserExists(int id);

}
