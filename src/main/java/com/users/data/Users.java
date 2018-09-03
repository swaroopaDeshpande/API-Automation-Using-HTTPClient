package com.users.data;

public class Users {
	// class variables,global varibles

	String name;
	String job;
	String id;
	String createdAt;
	
	public Users()
	{
		
	}

	public Users(String name, String job) {
		// global=local vars
		this.name = name;
		this.job = job;
		this.id=id;
		this.createdAt=createdAt;
	}

	

	// define getter and setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
