package com.example.demo.friends;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Method that is used to link to the Friend table and its main ID type to the database.
 *
 */
@Repository
@EnableJpaRepositories
public interface FriendRepository extends JpaRepository <Friend, Integer> 
{


}
