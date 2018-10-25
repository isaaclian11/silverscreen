package com.example.demo.friends;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface FriendRepository extends JpaRepository <Friend, Integer> 
{

}
