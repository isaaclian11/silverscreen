package com.example.demo.review;
import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table (name = "review")
public class Review {


	@Id
	@Column (name = "id")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private int id;

	@Column (name = "user_who_posted")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String user_who_posted;
	
	@Column(name = "movieTitle")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String movieTitle;
	
	//the users review in string from
	@Column (name = "review")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String review;

	//the score that the user gave the film
	
	@Column (name = "score")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private int score;
	
	//the date that the user posted the review
	@Column (name = "date")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String date;

	@Column (name = "movieID")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private int movieID;

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	@Column (name = "parentID")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private int parentID;

	public String getPosterID() {
		return posterID;
	}

	public void setPosterID(String posterID) {
		this.posterID = posterID;
	}

	@Column (name = "posterID")
	@NotFound (action = NotFoundAction.EXCEPTION)
	private String posterID;
	
	//the username of the user who posted that review
	public void setId(int id) {
		this.id = id;
	}
	
	public int getScore() {
		return score;
	}
	private void setScore(int score) {
		this.score = score;
	}


	public int getId(){
		return id;
	}
	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getDate() {
		return date;
	}
	public void addDate(String date) {
		this.date = date;
	}

	public String getuser_who_posted() {
		return user_who_posted;
	}
	public void setUser_who_posted(String user_who_posted) {
		this.user_who_posted = user_who_posted;
	}
	public String getmovieTitle() {
		return movieTitle;
	}
	public void setmovieTitle(String movieTitle) {
		 this.movieTitle = movieTitle;
	}
	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

}

interface ReviewAndName{
	String getId();
	String getReview();
	String getuser_who_posted();
	String getposterID();
	String getScore();
	String getMovieID();
}

interface ReviewReplies{
	String getReview();
	String getuser_who_posted();
}

interface postReview{
	String getuser_who_posted();
	String getReview();
	int getScore();
	int getParentID();
	int getMovieID();
}


