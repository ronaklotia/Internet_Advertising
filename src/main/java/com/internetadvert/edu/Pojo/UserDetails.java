package com.internetadvert.edu.Pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="userdetails")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID", unique=true, nullable=false)
	private int userID;

	@Column(name="Name", length=30)
	private String name;

	@Column(name="Phone_NO", unique=true, nullable=false, length=30)
	private String phoneNo;

	@Column(name="Occupation", nullable=false, length=30)
	private String occupation;

	@Column(name="Gender", length=30)
	private String gender;

	@Column(name="Computer_Type", nullable=false, length=30)
	private String computerType;

	@Column(name="Email", unique=true, nullable=false, length=30)
	private String email;

	@OneToOne(fetch = FetchType.LAZY)
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getComputerType() {
		return computerType;
	}
	public void setComputerType(String computerType) {
		this.computerType = computerType;
	}



}
