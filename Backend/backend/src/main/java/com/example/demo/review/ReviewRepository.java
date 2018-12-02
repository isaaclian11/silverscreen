package com.example.demo.review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ReviewRepository extends JpaRepository <Review, Integer> {

    @Query(value = "select distinct r.movie_title, r.movieID, r.id, r.score, r.user_who_posted, r.review, r.posterID from review r, friends f " +
            "where (f.friend1=?1 or f.friend2=?1) and r.parentID=0 order by r.id desc", nativeQuery = true)
    public List<ReviewAndName> findReviewByUsername(String username);

    @Query(value = "select distinct r.user_who_posted, r.review from review r where r.parentID=?1", nativeQuery = true)
    public List<ReviewReplies> findReplies(int id);

    @Query(value = "insert into review(user_who_posted, score, movieID, parentID, review) values (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    public void postReview(String user_who_posted, int score, int movieID, int parentID, String review);
}
