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
	 * Value that is used to retrieve a film from The Movie Database API.
	 */
	@Column (name = "movieID")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private int movieID;

	/**
	 * Method that returns an id that represents the movie that this reply will be posted on.
	 * @return
	 */
	public int getParentID() {
		return parentID;
	}

	/**
	 * Method that sets the parentID for a reply to a review or other comment. This is only used when a user
	 * is replying to a reivew thathas been posted or another comment on a review.
	 * @param parentID
	 */
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	/**
	 * Value that represents the ID of the review that this reply refers to. This is used
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
	 * Method that sets the value for the posterID to the String posterID. This is used to retrieve an image
	 * of a movie poster from The Movie Database API so that it can be displayed on the client.
	 * @param posterID
	 */
	public void setPosterID(String posterID) {
		this.posterID = posterID;
	}

	/**
	 * Value that represents an ID that can be used by the Movie Database API in order to retrieve a poster of
	 * that specific film to be displayed on the client. 
	 */
	@Column (name = "posterID")
	@NotFound (action = NotFoundAction.EXCEPTION)
	private String posterID;
	
	/**
	 * Method that sets the id number for the review.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Method that retrieves the score that a user gave a specific film.
	 * @return
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Method that is used to add/change a score that a user gave to a movie.
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}


	/**
	 * Method that returns the id of a certain row/column in the database. This value is also used to link a
	 * reply to a film review.
	 * @return
	 */
	public int getId(){
		return id;
	}
	/**
	 * Method that returns the review from a user in the database.
	 * @return
	 */
	public String getReview() {
		return review;
	}

	/**
	 * Method that adds the review from a user to a new row in the reviews table in the database.
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
	 * Method that returns the user who posted the review or reply.
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
	 * Method that sets the name of the movie to String movieTitle and puts it into the Review table in the 
	 * database.
	 * @param movieTitle
	 */
	public void setMovie_title(String movieTitle) {
		 this.movie_title = movieTitle;
	}
	/**
	 * Method that returns the Movie ID from the database. This value is used in The Movie Database API to
	 * pull information from this database.
	 * @return
	 */
	public int getMovieID() {
		return movieID;
	}

	/**
	 * Method that sets the movie ID to int movieID and puts it in to the Review table in the database.
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
	String getParentID();
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


