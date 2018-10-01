package com.login;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class LoginController {
	@Autowired
	private LoginRepository loginsRepository;
	
	private final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(method = RequestMethod.POST, path = "/Login")
	public String checkUserName(@PathVariable("username") String username) {
		logger.info("Entered the controller layer");
		String results = LoginRepository.findByUsername(username);
		return results;
	}
	@RequestMapping(method = RequestMethod.POST, path = "/Login")
	public String checkPassword(@PathVariable("password") String password) {
		logger.info("Entered the controller layer");
		String result = LoginRepository.findByPassword(password);
	}	
	/*
	@Requestmapping(method = RequestMethod.GET, path = "/Login")
	public String addUser(Login user, password) {
		LoginRepository.addUser(user, password)
	}
	*/

}
