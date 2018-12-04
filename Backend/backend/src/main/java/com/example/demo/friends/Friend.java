package com.example.demo.friends;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table (name = "friends")
public class Friend 
{
	/**
	 * Value that represents the column number in the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Integer id;
	
	/**
	 * String that represents the first friend.
	 */
	@Column (name = "friend1")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String friend1;
	
	/**
	 * String that represent the second friend.
	 */
	@Column(name = "friend2")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String friend2;
	
	public int getID() 
	{
		return id;
	}
	
	/**
	 * Method that returns the first friend in a specific column in the database.
	 * @return
	 */
	public String getFriend1() 
	{
		return friend1;
	}
	/**
	 * Return the second friend from a specific column in the database.
	 * @return
	 */
	public String getFriend2() 
	{
		return friend2;
	}

	/**
	 * Method that sets the user passed through as the first friend in the connection between two users that
	 * are friends. 
	 * @param friend1
	 */
	public void setFriend1(String friend1) 
	{
		this.friend1 = friend1;
	}

	/**
	 * Method that sets the user passed through the string as the second friend. This person will be connected
	 * to friend 1, meaning that they are friends with one another.
	 * @param friend2
	 */
	public void setFriend2(String friend2) 
	{
		this.friend2 = friend2;
	}
}

