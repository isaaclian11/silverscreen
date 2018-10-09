package com.example.demo.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {
//	Collection<Login> findAll();
	//calls for SQL to attempt to find the username inputted
	//@Query(value="SELECT * FROM Login WHERE username")
	//Collection<Login> findByUsername(@Param("username") String username);
	//calls for SQL to attempt to find the password
//	Collection<Login>findByPassword(@Param("password") String password);
	//if this username and password match, then we need to send success back to the client
	//otherwise, we will want to send a failure back to the client
	
	//post request to the server
//	Login save (Login login);
	
}