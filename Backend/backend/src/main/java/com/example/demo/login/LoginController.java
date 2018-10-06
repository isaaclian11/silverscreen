package com.example.demo.login;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {
	@Autowired
	private LoginRepository loginsRepository;
	
	private final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(method = RequestMethod.POST, path = "/Login")
	public String checkUserName(@RequestBody String username) {
		String status = "";
		try {
			logger.info("Entered the controller layer");
			//gets usernames that match from SQL
			Collection<Login> results = loginsRepository.findByUsername(username);
			//checks if that username is present in our data
			if (results.isPresent()) {
				//since it was found, there was a user with this name
				status = "username was found, success";
			}
		}
		//checks for IllegalArgumentException (this means we did not find a username with the name given
		catch (IllegalArgumentException e) {
			//since it couldn't be found, the user doesn't currently exist in our datatable
			status = "This username does not exist";
		}
		return status;
	}
	@RequestMapping(method = RequestMethod.POST, path = "/Login")
	public String checkPassword(@PathVariable("password") String password) {
		String status = "";
		logger.info("Entered the controller layer");
		try {
			//figure out how to pass through username
			if (checkUserName(username)).equals("This username does not exist")) {
				status = "This username does not exist";
			}
			//need to find out how to check specific user
			Optional<Login> result = LoginRepository.findByID(password);
			if (result.isPresent()) {
				status = "Success, this password matches";
			}
		}
		catch (IllegalArgumentException e) {
			status = "The password is incorrect";
		}
		return status;
	}	
	/*
	@Requestmapping(method = RequestMethod.GET, path = "/Login")
	public String addUser(Login user, password) {
		LoginRepository.addUser(user, password)
	}
	*/

}
