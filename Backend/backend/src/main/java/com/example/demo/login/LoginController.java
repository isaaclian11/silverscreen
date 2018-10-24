package com.example.demo.login;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.YamlJsonParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
	
	@Autowired
	LoginRepository loginsRepository;
	
	private final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@PostMapping(path="/user")
	public String postUser(@RequestBody Login login) {
        logger.info("Entered into Controller Layer");
		loginsRepository.save(login);
        logger.info("Saved:" + login);
		return "success";
	}
	
    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public List<Login> getAllUsers() {
        logger.info("Entered into Controller Layer");
        List<Login> results = loginsRepository.findAll();
        logger.info("Number of Records Fetched:" + results.size());
        return results;
    }
    
	@RequestMapping(method = RequestMethod.POST, path = "/users111")
	public jsonResponse checkUserName(@RequestBody String loginInformation)
	{
		System.out.println("==========POST method called");
		System.out.println("username = " + loginInformation);
		
		String delims = "[\"]";
		String[] tokens = loginInformation.split(delims);

		String userName = tokens[3];
		String password = tokens[7];
		System.out.println("username = " + userName);
		System.out.println("password = " + password);
		
        logger.info("Entered into Controller Layer");
        List<Login> results = loginsRepository.findAll();
        logger.info("========Number of Records Fetched:" + results.size());
        System.out.println(results.toString()); 
        
        int i =0; 
        while (i < results.size())
        {
        	System.out.println(results.get(i).getUsername());
        	System.out.println(results.get(i).getPassword());
        	
        	String uName = results.get(i).getUsername();
        	String uPass = results.get(i).getPassword();

        	String firstname = results.get(i).getFirstname();
        	String lastname = results.get(i).getLastname();
        	
        	if (userName.equals(uName) && password.equals(uPass))
        	{
        		jsonResponse jsonResponse = new jsonResponse(results.get(i), "success");
        		return jsonResponse;
        		//System.out.println("FOUND = " + found);
        	}
        	
        	i++;
        	//System.out.println("**************");
        }
        jsonResponse jsonResponse = new jsonResponse("failure");
        return jsonResponse;
	}
	@RequestMapping(method = RequestMethod.POST, path = "/login/add")
	public jsonResponse addUser(@RequestBody Login login) {
		if (loginsRepository.existsById(login.getUsername()) == true){
			jsonResponse x = new jsonResponse("failure");
			return x;
		}
		else {
			loginsRepository.save(login);
			jsonResponse jsonResponse = new jsonResponse(login, "success");
			return jsonResponse;
		}
	}
}
