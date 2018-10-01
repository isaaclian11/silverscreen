package com.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table (name = "Login")
public class Login {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "username")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String username;
	
	//gets the row of passwords
	@Column (name = "password")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String password;

	public String getUserName() {
		return username;
	}
	public String getPassword() {
		return password;
	}
}
