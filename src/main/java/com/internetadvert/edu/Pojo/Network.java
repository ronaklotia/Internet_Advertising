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
import javax.persistence.Table;

@Entity
@Table(name="network")
public class Network {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="NETWORK_ID", unique=true, nullable=false)
	private int networdID;
	
	@Column(name="NETWORK_NAME", unique=true, nullable=false)
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="adminID", nullable=false)
	private Admin admin;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="network", cascade=CascadeType.ALL)
	private List<AdverstisingCompany> advert = new ArrayList<AdverstisingCompany>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="network", cascade=CascadeType.ALL)
	private List<Exchange> exchange = new ArrayList<Exchange>();
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public int getNetwordID() {
		return networdID;
	}

	public void setNetwordID(int networdID) {
		this.networdID = networdID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
