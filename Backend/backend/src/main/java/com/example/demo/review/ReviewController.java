package com.example.demo.review;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
	@Autowired
	ReviewRepository reviewRepository; 
	
	//finds all reviews
	@RequestMapping(method = RequestMethod.GET, path = "/review/reviews")
	public List <Review> getReviews() {
		List<Review> results = reviewRepository.findAll();
		return results;
	}
	// adds a review by a user to the server
	@RequestMapping (method = RequestMethod.POST, path = "/review/newReview")
	public String addNewReview(Review review) {
		reviewRepository.save(review);
		return "New Review by " + review.getUser() + " successfully uploaded.";
	}


}
