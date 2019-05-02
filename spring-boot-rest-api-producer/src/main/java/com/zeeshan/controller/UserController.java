package com.zeeshan.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zeeshan.model.User;
import com.zeeshan.service.UserService;
import com.zeeshan.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UserController {

	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/*----------------------------------Retrieving List of Users--------------*/

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		logger.info("Fetching all users");
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	/*----------------------------------Retrieving User by id--------------*/

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findUserById(@PathVariable("id") int id) {
		logger.info("Fetching user with id {}", id);
		if (!userService.isUserExists(id)) {
			logger.error("User not found with id{} ", id);
			return new ResponseEntity(new CustomErrorType("User not found with id {} " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		User user = userService.findById(id);
		logger.info("User is ", user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/*----------------------------------Creating a User--------------*/

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("create user{} ", user);
		if (userService.isUserExists(user.getId())) {
			logger.error("Error creating user, a user already exists with user name: " + user.getName());
			return new ResponseEntity(
					new CustomErrorType("unable to create a user with name " + user.getName() + " already exists"),
					HttpStatus.CONFLICT);

		}

		userService.saveUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());

		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	/*----------------------------------Updating a User--------------*/

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") int id, @RequestBody User user) {
		logger.info("Updating User with id {}", id);
		User user1 = userService.findById(id);
		if (user1 == null) {
			return new ResponseEntity(
					new CustomErrorType("Unable to update, No user existed with id" + id) + " not found ",
					HttpStatus.NOT_FOUND);
		}
		user1.setAge(user.getAge());
		user1.setName(user.getName());
		user1.setSalary(user.getSalary());
		userService.updateUser(user1);
		return new ResponseEntity<User>(user1, HttpStatus.OK);
	}

	/*----------------------------------Delete a User--------------*/

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUserById(@PathVariable("id") int id) {
		logger.info("Deleting User with id {}", id);
		User user = userService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity<>(new CustomErrorType("Unable to delete. User with id {}" + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	/*----------------------------------Delete all Users--------------*/

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllUsers() {
		logger.info("Deleting all Users");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}
