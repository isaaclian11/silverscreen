package com.example.demo.review;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table (name = "Review")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "filmName")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String filmName;
	
	//the users review in string from
	@Column (name = "review")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String review;
	//the score that the user gave the film
	@Column (name = "score")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private Integer score;
	//the date that the user posted the review
	@Column (name = "date")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String datePosted;
	//the username of the user who posted that review
	@Column (name = "userWhoPosted")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String userWhoPosted;
	
	public Integer returnScore() {
		return score;
	}
	private Integer setScore() {
		return this.score = score;
	}
	public String getReview() {
		return review;
	}
	public String setReview() {
		return this.review = review;
	}
	public String getDate() {
		return datePosted;
	}
	public String getUser() {
		return userWhoPosted;
	}
	public String addUser() {
		return this.userWhoPosted = userWhoPosted;
	}
	public String getFilmName() {
		return filmName;
	}
	public String addFilm() {
		return this.filmName = filmName;
	}
	}