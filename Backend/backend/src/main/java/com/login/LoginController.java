package com.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
	@Autowired
	private LoginRepository loginrepository;
	

}
