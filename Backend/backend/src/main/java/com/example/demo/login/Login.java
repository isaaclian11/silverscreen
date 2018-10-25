package com.example.demo.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.aspectj.weaver.ast.Not;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table (name = "login")
public class Login {
	@Id
	@Column(name = "username")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String username;
	
	//gets the row of passwords
	@Column (name = "password")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String password;

	@Column(name = "firstname")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String firstname;

	@Column(name = "lastname")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String lastname;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
