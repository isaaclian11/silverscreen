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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Integer id;
	
	@Column (name = "friend1")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String friend1;
	
	@Column(name = "friend2")
	@NotFound(action = NotFoundAction.EXCEPTION)
	private String friend2;
	
	public int getID() 
	{
		return id;
	}
	
	public String getFriend1() 
	{
		return friend1;
	}
	
	public String getFriend2() 
	{
		return friend2;
	}

	public void setFriend1(String friend1) 
	{
		this.friend1 = friend1;
	}

	public void setFriend2(String friend2) 
	{
		this.friend2 = friend2;
	}
}

