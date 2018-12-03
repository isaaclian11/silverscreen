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
@Table (name = "review")
public class Review {


	/**
	 * Integer value that is assigned to each row in the database.
	 */
	@Id
	@Column (name = "id")
	@NotFound(action = NotFoundAction.EXCEPTION)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	/**
	 * String that represents the name of the user who posted a review.
	 */
	@Column (name = "user_who_posted")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String user_who_posted;
	
	/**
	 * Name of the movie that the user is reviewing.
	 */
	@Column (name = "movie_title")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String movie_title;
	
	/**
	 * The text review that the user has about a specific movie.
	 */
	@Column (name = "review")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String review;

	/**
	 * The rating that a user gave to this movie.
	 */
	@Column (name = "score")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private int score;
	
	/**
	 * The date when the review was posted.
	 */
	@Column (name = "date")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String date;

	/**
	 * Value that is used to retrieve a film from The Movie Database API
	 */
	@Column (name = "movieID")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private int movieID;

	/**
	 * Value that represents the id of the movie that this reply correlates to.
	 * @return
	 */
	public int getParentID() {
		return parentID;
	}

	/**
	 * Value that sets the parentID from a reply. This is only use when user is replying to a reivew that
	 * has been posted.
	 * @param parentID
	 */
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	/**
	 * Value that represents the ID of the ID of the review that this reply is referring to. This is used
	 * to know where the reply will go on the client side.
	 */
	@Column (name = "parentID")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private int parentID;

	/**
	 * Method that returns the URL that is used to link to a poster that will be displayed on the client.
	 * @return
	 */
	public String getPosterID() {
		return posterID;
	}

	/**
	 * Value that sets the value for the posterID. This is used to retrieve an image of the poster for the client.
	 * @param posterID
	 */
	public void setPosterID(String posterID) {
		this.posterID = posterID;
	}

	/**
	 * Value that represents the 
	 */
	@Column (name = "posterID")
	@NotFound (action = NotFoundAction.EXCEPTION)
	private String posterID;
	
	/**
	 * Value that sets the id number for the review.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Retrieves the score of a specific film.
	 * @return
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Used to add/change a score that a user gave to a movie.
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}


	/**
	 * Returns the id of a certain row/column in the database.
	 * @return
	 */
	public int getId(){
		return id;
	}
	/**
	 * Returns the review from a user in the database.
	 * @return
	 */
	public String getReview() {
		return review;
	}

	/**
	 * Adds the review from a user to a new row of reviews in the database.
	 * @param review
	 */
	public void setReview(String review) {
		this.review = review;
	}

	/**
	 * Method that returns the date that a review was posted.
	 * @return
	 */
	public String getDate() {
		return date;
	}
	/**
	 * Method that adds a date. Used when adding a review to the database or modifying a review in the database.
	 * @param date
	 */
	public void addDate(String date) {
		this.date = date;
	}

	/**
	 * returns the user who posted the review for a specific review in the database.
	 * @return
	 */
	public String getuser_who_posted() {
		return user_who_posted;
	}
	/**
	 * Method that is used to set the user who posted a film when creating a review in the database.
	 * @param user_who_posted
	 */
	public void setUser_who_posted(String user_who_posted) {
		this.user_who_posted = user_who_posted;
	}
	/**
	 * Method that retrieves the name of a film from a review in the database.
	 * @return
	 */
	public String getmovieTitle() {
		return movie_title;
	}
	/**
	 * Method that adds the name of the movie to the database.
	 * @param movieTitle
	 */
	public void setMovie_title(String movieTitle) {
		 this.movie_title = movieTitle;
	}
	/**
	 * Method that returns the Movie ID from the database. This can be used when replying to a review.
	 * @return
	 */
	public int getMovieID() {
		return movieID;
	}

	/**
	 * This method is used to set the movie ID when adding a new review to the database.
	 * @param movieID
	 */
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

}

interface ReviewAndName{
	String getMovie_title();
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


