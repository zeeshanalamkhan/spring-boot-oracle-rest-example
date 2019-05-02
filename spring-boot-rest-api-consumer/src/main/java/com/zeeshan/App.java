package com.zeeshan;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.zeeshan.model.User;

/**
 * 
 * @author ZEESHAN KHAN
 *
 */
@SuppressWarnings("unchecked")
public class App {

	public static final String REST_SERVICE_URI = "http://localhost:8555/springbootrest/api";

	/* GET */
	public static void listAllUsers() {

		System.out.println("----------Tetsing all users api----------");
		RestTemplate restTemplate = new RestTemplate();

		List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI + "/user/",
				List.class);
		if (usersMap != null) {
			for (LinkedHashMap<String, Object> map : usersMap) {
				System.out.println("User: " + map.get("id") + " -- " + map.get("name") + " -- " + map.get("age")
						+ " -- " + map.get("salary"));
			}
		} else {
			System.out.println("no user exists");
		}
	}

	/* GET BY ID */
	public static void findUserById(int id) {
		System.out.println("-----Testing find user by id api----- ");
		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForObject(REST_SERVICE_URI + "/user/" + id, User.class);
		System.out.println(user);
	}

	/* POST */
	public static void createUser(User user) {
		System.out.println("-----Testing create new user api----- ");
		RestTemplate restTemplate = new RestTemplate();
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/user/", user, User.class);
		System.out.println("Location= " + uri.toASCIIString());
	}

	/* PUT */
	public static void updateUser(int id, User user) {
		System.out.println("-----Testing update user api----- ");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(REST_SERVICE_URI + "/user/" + id, user);
		System.out.println("User upadated");
		findUserById(user.getId());
	}

	/* DELETE BY ID */
	public static void deleteUserById(int id) {
		System.out.println("-----Testing delete user by id api----- ");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/user/" + id);
	}

	/* DELETE ALL USERS */
	public static void deleteAllUsers() {
		System.out.println("-----Testing delete user api----- ");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/user/");
	}

	public static void main(String[] args) {
		/*
		 * User user = new User(); user.setName("ZAHEER"); user.setAge(20);
		 * user.setSalary(52000); createUser(user);
		 */

//	findUserById(4);

		User user = new User();
		
		user.setName("Zaheer");
		updateUser(4, user);

		// listAllUsers();
		// deleteUserById(1);
		// deleteAllUsers();
	}
}
