package com.internetadvert.edu.Pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="userstable", uniqueConstraints=@UniqueConstraint(columnNames={"USER_NAME", "PASS_WORD"}))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID", unique=true, nullable=false)
	private int userID;
	
	@Column(name="USER_NAME", unique=true, nullable=false, length=30)
	private String username;
	
	@Column(name="PASS_WORD", nullable=false, length=200)
	private String password;
	
	@Column(name="ROLE", nullable=false, length=30)
	private String role;	
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private UserDetails userDetails;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user1", cascade = CascadeType.ALL)
	private Admin admin;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Exchange exchange;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private AdverstisingCompany adverstisingCompany;
	
	
	
	public AdverstisingCompany getAdverstisingCompany() {
		return adverstisingCompany;
	}
	public void setAdverstisingCompany(AdverstisingCompany adverstisingCompany) {
		this.adverstisingCompany = adverstisingCompany;
	}
	public Exchange getExchange() {
		return exchange;
	}
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
	
}
