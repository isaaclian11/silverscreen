package com.example.isaacsanga.login;

public class MovieModel {
    private int movieID;
    private String movieTitle;
    private String moviePoster;
    private String username;

    public MovieModel(int movieID, String movieTitle, String moviePoster, String username) {
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.moviePoster = moviePoster;
        this.username = username;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
