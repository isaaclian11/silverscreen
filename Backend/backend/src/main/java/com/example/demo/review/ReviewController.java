package com.example.demo.review;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
	/**
	 * Repository that this controller will link to
	 */
	@Autowired
	ReviewRepository reviewRepository; 
	
	/**
	 * Method that returns all of the review that are found. 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/review/reviews")
	public List <Review> getReviews() 
	{
		List<Review> results = reviewRepository.findAll();
		return results;
	}

	/**
	 * Method that adds a new review to the database.
	 * @param review
	 * @return
	 */
	@RequestMapping (method = RequestMethod.POST, path = "/review/newReview")
	public postReviewJson addNewReview(@RequestBody Review review)
	{
		Review savedReview = reviewRepository.save(review);
		return new postReviewJson("success");
	}

	/**
	 * Method that finds reviews but only of people that you are friends with. This is because we are 
	 * wanting the feed of a user to only show reviews from their friends.
	 * @param username
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/review/friendsReview")
	public jsonResponse findReviewsOfUserFriends(@RequestBody Review username)
	{
		return new jsonResponse(reviewRepository.findReviewByUsername(username.getuser_who_posted()));
	}

	/**
	 * This method gets the replies that users have posted to reviews.
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/review/replies")
	public ReplyJson findRepliesOfReviews(@RequestBody Review id){
		return new ReplyJson(reviewRepository.findReplies(id.getId()));
	}


}