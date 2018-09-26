package com.login;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table (name = "Login")
public class Login {
	@Id
	@GeneratedValue = (strategy = GenerationType.IDENTITY);
	//gets the number for each row
	private integer id;
	
	//gets the row of usernames
	@NotEmpty
	private String username;
	
	//gets the row of passwords
	@NotEmpty
	private String password;

}
