package com.example.demo.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import antlr.collections.List;

@RestController
public class ChatController {
	@Autowired
	ChatRepository chatRepository;
	
	private final Logger logger = LoggerFactory.getLogger(ChatController.class);
	
	/**
	 * Method that returns all of the chat messages from the server. This is used when a account/user first
	 * joins the server.
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/chat")
	public java.util.List<Chat> getAllHistory()
	{
        logger.info("Entered into Controller Layer");
      //returns the list of chat History
      return chatRepository.findAll();
	}
	/**
	 * Method that is used to save a chat message to the database. This is used for the history.
	 * @param chat
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/chat/add")
	public jsonResponse saveChat(@RequestBody Chat chat) {
		logger.info("Entered into Controller layer");
		long numberofMessages = chatRepository.count();
		//saves the message
		chatRepository.save(chat);
		//checks to see if the object was successfully added
		if (chatRepository.count() == (numberofMessages + 1)) {
			//returns to the user a success message (since the message has been saved)
			jsonResponse jsonResponse = new jsonResponse (chat,"Chat successfully added");
			return jsonResponse;
		}
		else {
			//since it did not save successfully, it will send a failure message
			jsonResponse x = new jsonResponse (chat, "failure");
			return x;
		}
	}

}
