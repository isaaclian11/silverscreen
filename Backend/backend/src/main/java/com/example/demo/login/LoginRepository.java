package com.example.demo.login;

import com.example.demo.friends.friendListJson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Method that links to the Login table and its main ID in the database.
 *
 */
@Repository
public interface LoginRepository extends JpaRepository<Login, String> 
{
    @Query(value = "SELECT U.username FROM users U" +
            "  LEFT JOIN friends F" +
            "  ON U.username = F.friend2" +
            "  WHERE F.friend1 = ?1 " +
            "UNION " +
            "SELECT U.username FROM users U" +
            "  LEFT JOIN friends F" +
            "  ON U.username = F.friend1" +
            "  WHERE F.friend2 = ?1", nativeQuery = true)
    public List<myFriends> findMyFriends(String username);
}