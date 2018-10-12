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
@Table (name = "review3")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Integer id;
	
	@Column (name = "user_who_posted")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String user_who_posted;
	
	@Column(name = "film_name")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String film_name;
	
	//the users review in string from
	@Column (name = "review")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String review;
	//the score that the user gave the film
	
	/*@Column (name = "score")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private Integer score;
	//the date that the user posted the review
	@Column (name = "date")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String datePosted;
	//the username of the user who posted that review
	
	public Integer returnScore() {
		return score;
	}
	private void setScore(int score) {
		this.score = score;
	}
	*/
	public int getID() {
		return id;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	/*
	public String getDate() {
		return datePosted;
	}
	public void addDate(String datePosted) {
		this.datePosted = datePosted;
	}
	*/
	public String getUser() {
		return user_who_posted;
	}
	public void addUser(String user_who_posted) {
		this.user_who_posted = user_who_posted;
	}
	public String getFilmName() {
		return film_name;
	}
	public void addFilm(String film_name) {
		 this.film_name = film_name;
	}
}