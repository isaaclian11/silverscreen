package com.example.demo.friends;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.login.myFriendsJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.YamlJsonParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.login.Login;


@RestController
public class FriendController 
{
	/**
	 * The repository that the controller links to. This is used so that it can connect to the correct table
	 * in the database.
	 */
	@Autowired
	FriendRepository friendsRepository;
	private final Logger logger = LoggerFactory.getLogger(FriendController.class);

	/**
	 * Method that will adds friends to the database.
	 * @param friend
	 * @return
	 */
	@PostMapping (path = "/friends")
	public String postFriend(@RequestBody Friend friend) 
	{
        logger.info("Entered into Controller Layer");
		friendsRepository.save(friend);
        logger.info("Saved:" + friend);
		
        return "success";
	}
	
	/**
	 * Method that will get all friends on the database.
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/allFriends")
    public List<Friend> getAllFriends() 
    {
        logger.info("Entered into Controller Layer");
        List<Friend> results = friendsRepository.findAll();
        logger.info("Number of Records Fetched:" + results.size());
        
        return results;
    }





}

