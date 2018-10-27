package com.example.demo.review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ReviewRepository extends JpaRepository <Review, Integer> {

    @Query(value = "select distinct r.user_who_posted, r.review from review r, friends f " +
            "where r.user_who_posted<>?1 and(f.friend1=?1 or f.friend2=?1)", nativeQuery = true)
    public List<ReviewAndName> findReviewByUsername(String username);

}
