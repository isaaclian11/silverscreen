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
	@RequestMapping(method = RequestMethod.GET, path = "/chat")
	public java.util.List<Chat> getAllHistory()
	{
        logger.info("Entered into Controller Layer");
      //returns the list of chat History
      return chatRepository.findAll();
	}

}
