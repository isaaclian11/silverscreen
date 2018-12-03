package com.example.demo.review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@EnableJpaRepositories
/**
 * Method that links to the database, specifying the review table (since we want to add this
 * information to reviews) and the type of ID it uses.
 *
 */
public interface ReviewRepository extends JpaRepository <Review, Integer> {

    @Query(value = "select distinct r.movie_title, r.movieID, r.id, r.score, r.user_who_posted, r.review, r.posterID from review r, friends f " +
            "where (f.friend1=?1 or f.friend2=?1) and r.parentID=0 order by r.id desc", nativeQuery = true)
    public List<ReviewAndName> findReviewByUsername(String username);

    @Query(value = "select distinct r.user_who_posted, r.review from review r where r.parentID=?1", nativeQuery = true)
    public List<ReviewReplies> findReplies(int id);

    @Query(value = "insert into review(user_who_posted, score, movieID, parentID, review) values (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    public void postReview(String user_who_posted, int score, int movieID, int parentID, String review);

    @Query(value = "select r.parentID, r.movie_title, r.movieID, r.id, r.score, r.user_who_posted, r.review, r.posterID from review r " +
            "where r.user_who_posted=?1 order by r.id desc", nativeQuery = true)
    public List<ReviewAndName> myReviews(String username);

    @Modifying
    @Transactional
    @Query(value = "delete from review where id=?1 or parentID=?1", nativeQuery = true)
    public int deleteReviewsAndReplies(int id);
}
