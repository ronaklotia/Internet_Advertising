package com.internetadvert.edu.Pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="Admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ADMIN_ID", unique=true, nullable=false)
	private int adminID;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private User user1;
	
	@Column(name="NAME", unique=true, length=30)
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="admin", cascade=CascadeType.ALL)
	private List<Network> network = new ArrayList<Network>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="admin", cascade=CascadeType.ALL)
	private List<Employee> employeeList = new ArrayList<Employee>();
	
//	@OneToMany(fetch=FetchType.LAZY, mappedBy="admin", cascade=CascadeType.ALL)
//	private List<Exchange> exList = new ArrayList<Exchange>();
	
//	@OneToMany(fetch=FetchType.LAZY, mappedBy="admin", cascade=CascadeType.ALL)
//	private List<AdverstisingCompany> adverstisingCompanies = new ArrayList<AdverstisingCompany>();
	
	
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	public List<Network> getNetwork() {
		return network;
	}
	public void setNetwork(List<Network> network) {
		this.network = network;
	}
	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser1() {
		return user1;
	}
	public void setUser1(User user1) {
		this.user1 = user1;
	}
	
	

	
	
}
