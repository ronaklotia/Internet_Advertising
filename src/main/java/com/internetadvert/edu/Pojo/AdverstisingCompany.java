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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="AdvertisingCompany")
public class AdverstisingCompany {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="COMPANY_ID", unique=true, nullable=false)
	private int companyID;
	
	@Column(name="COMPANY_NAME", nullable=false)
	private String name;
	
	@OneToOne(fetch = FetchType.LAZY)
	private User user;
	
//	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
//	@JoinColumn(name="adminID", nullable=false)
//	private Admin admin;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="company", cascade=CascadeType.ALL)
	private List<Catogries> catogries = new ArrayList<Catogries>();
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="networdID", nullable=false)
	private Network network;
	

	@OneToOne(fetch = FetchType.LAZY)
	private Employee employee;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public List<Catogries> getCatogries() {
		return catogries;
	}
	public void setCatogries(List<Catogries> catogries) {
		this.catogries = catogries;
	}
	public Network getNetwork() {
		return network;
	}
	public void setNetwork(Network network) {
		this.network = network;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getCompanyID() {
		return companyID;
	}
	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
