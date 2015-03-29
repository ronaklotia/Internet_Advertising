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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Exchange")
public class Exchange {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="EXCHANGE_ID", unique=true, nullable=false)
	private int exchangeID;
	
	@Column(name="EXC_NAME", nullable=false)
	private String exchangeName;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="networdID", nullable=false)
	private Network network;
//	
//	@OneToOne(fetch = FetchType.LAZY)
//	@PrimaryKeyJoinColumn
//	private Admin admin;
	
	@OneToOne(fetch = FetchType.LAZY)
	private User user;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="exchange", cascade=CascadeType.ALL)
	private List<Website> websiteList = new ArrayList<Website>();
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="exchangeList")
	private List<Bid> bidList = new ArrayList<Bid>();	
	
	@OneToOne(fetch = FetchType.LAZY)
	private Employee employee;
	
	
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Network getNetwork() {
		return network;
	}
	public void setNetwork(Network network) {
		this.network = network;
	}
	public List<Website> getWebsiteList() {
		return websiteList;
	}
	public void setWebsiteList(List<Website> websiteList) {
		this.websiteList = websiteList;
	}
	public List<Bid> getBidList() {
		return bidList;
	}
	public void setBidList(List<Bid> bidList) {
		this.bidList = bidList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getExchangeID() {
		return exchangeID;
	}
	public void setExchangeID(int exchangeID) {
		this.exchangeID = exchangeID;
	}
	public String getExchangeName() {
		return exchangeName;
	}
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	
	
}
