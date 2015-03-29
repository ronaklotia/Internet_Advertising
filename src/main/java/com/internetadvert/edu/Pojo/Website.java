package com.internetadvert.edu.Pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Website")
public class Website {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="WEBSITE_ID", unique=true, nullable=false)
	private int websiteID;
	
	@Column(name="WEBSITE_NAME",unique=true, nullable=false)
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="exchangeID", nullable=false)
	private Exchange exchange;
		
	public Exchange getExchange() {
		return exchange;
	}
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}
	public int getWebsiteID() {
		return websiteID;
	}
	public void setWebsiteID(int websiteID) {
		this.websiteID = websiteID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
