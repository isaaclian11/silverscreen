package com.example.demo.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> 
{
    @Query(value = "", nativeQuery = true)
    public myFriendsJSON findMyFriends(String username);
}