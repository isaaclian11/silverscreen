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
	/**
	 * The username is the name that the user goes by when logged into the application. 
	 */
	@Id
	@Column(name = "username")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String username;
	
	/**
	 * Value that represents the account's password.
	 */
	@Column (name = "password")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String password;

	/**
	 * The first name of the user associated with the account.
	 */
	@Column(name = "firstname")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String firstname;

	/**
	 * The last name of the user associated with the account.
	 */
	@Column(name = "lastname")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String lastname;
	
	/**
	 * Method that retrieves the username.
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Method that allows the username to be changed to the String username.
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Method that retrieves the account's password.
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	

	/**
	 * Method that changes the password upon request to the String password.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Retrieves the first name of a user on an account.
	 * @return
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Retrieves the last name of a user on an account.
	 * @return
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Method that allows for the first name on an account to be changed to the value in String firstname.
	 * @param firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Method that allows for the last name on an account to be changed to the value in the String lastname.
	 * @param lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
interface myFriends{
	String getUsername();
}

