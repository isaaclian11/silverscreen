package com.example.demo.review;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List <Review> getReviews() 
	{
		List<Review> results = reviewRepository.findAll();
		return results;
	}

	@RequestMapping (method = RequestMethod.POST, path = "/review/newReview")
	public Review addNewReview(@RequestBody Review review)
	{
		Review savedReview = reviewRepository.save(review);
		return savedReview;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/review/friendsReview")
	public jsonResponse findReviewsOfUserFriends(@RequestBody Review username)
	{
		return new jsonResponse(reviewRepository.findReviewByUsername(username.getuser_who_posted()));
	}

	@RequestMapping(method = RequestMethod.POST, path = "/review/replies")
	public ReplyJson findRepliesOfReviews(@RequestBody Review id){
		return new ReplyJson(reviewRepository.findReplies(id.getId()));
	}

}
