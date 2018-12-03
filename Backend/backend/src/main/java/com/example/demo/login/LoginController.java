package com.example.demo.login;

import java.util.ArrayList;
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

import com.example.demo.friends.Friend;


@RestController
public class LoginController {
	
	@Autowired
	LoginRepository loginsRepository;
	
	private final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * This method is used to 
	 * @param login
	 * @return
	 */
	@PostMapping(path = "/user")
	public String postUser(@RequestBody Login login) 
	{
        logger.info("Entered into Controller Layer");
		loginsRepository.save(login);
        logger.info("Saved:" + login);
		
        return "success";
	}
	
    @RequestMapping(method = RequestMethod.GET, path = "/users")
    /**
     * This method is used for returning all of the users. This is a debugging method
     * @return
     */
    public List<Login> getAllUsers() 
    {
        logger.info("Entered into Controller Layer");
        List<Login> results = loginsRepository.findAll();
        logger.info("Number of Records Fetched:" + results.size());
        
        return results;
    }
    /**
     * This method is used to determine if a user has an account. If they do, it then checks if the password given
     * matches the password for the account on the database.
     * @param loginInformation
     * @return
     */
	@RequestMapping(method = RequestMethod.POST, path = "/users111")
	public jsonResponse checkUserName(@RequestBody String loginInformation)
	{
		String delims = "[\"]";
		String[] tokens = loginInformation.split(delims);

		String userName = tokens[3];
		String password = tokens[7];
		
        logger.info("Entered into Controller Layer");
        List<Login> results = loginsRepository.findAll();
        
        int i = 0; 
        while (i < results.size())
        {	
        	String uName = results.get(i).getUsername();
        	String uPass = results.get(i).getPassword();

        	String firstname = results.get(i).getFirstname();
        	String lastname = results.get(i).getLastname();
        	
        	if (userName.equals(uName) && password.equals(uPass))
        	{
        		jsonResponse jsonResponse = new jsonResponse(results.get(i), "success");
        		return jsonResponse;
        	}
        	
        	i++;
        }
        
        jsonResponse jsonResponse = new jsonResponse("failure");
        return jsonResponse;
	}
	/**
	 * This method is used to add a new user to the database. This is used when a user wants to create a new
	 * account.
	 * @param login
	 * @return
	 */
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
	/**
	 * This method searches for other users by getting all users from the database and comparing them to
	 * the string.
	 * @param search
	 * @return
	 */
    @RequestMapping(method = RequestMethod.POST, path = "/searchFriends")
    public ArrayList<String> searchForUser(@RequestBody String search) 
    {
		String delims = "[\"]";
		String[] tokens = search.split(delims);

		String userName = tokens[3];
    	
        logger.info("Entered into Controller Layer");
        List<Login> results = loginsRepository.findAll();
       
        ArrayList<String> friendResults = new ArrayList<String>();
        
        for (int i = 0; i < results.size(); i++)
        {
        	if (results.get(i).getUsername().contains(userName))
        	{
        		friendResults.add(results.get(i).getUsername());
        	}
        }
        
        return friendResults;
    }
}
