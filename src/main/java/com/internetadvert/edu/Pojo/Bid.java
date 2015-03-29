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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Bid")
public class Bid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="BID_ID", unique=true, nullable=false)
	private int bidID;
	
	@Column(name="OCCUPATION", nullable=false)
	private String occupation;
	
	@Column(name="GENDER", nullable=false)
	private String gender;
	
	@Column(name="AGE", nullable=false)
	private String age;
	
	@Column(name="COMP_TYPE", nullable=false)
	private String computerType;
	
	@Column(name="ad1", nullable=false)
	private String ad1;

	@Column(name="Price", nullable=false)
	private int price;
	
	@Column(name="IMAGE_PATH", nullable=false)
	private String imagepath;
	
	@Column(name="IMAGE_NAME", nullable=false)
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="bid", cascade=CascadeType.ALL)
	private List<Invoice> invoiceList = new ArrayList<Invoice>();
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAd1() {
		return ad1;
	}

	public void setAd1(String ad1) {
		this.ad1 = ad1;
	}

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="productID", nullable=false)
	private Product product;	
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="exchange_Bid",
	   joinColumns={@JoinColumn(name="BID_ID", nullable=false, updatable=false)},
	   inverseJoinColumns={@JoinColumn(name="EXCHANGE_ID", nullable=false, updatable=false)})
	private List<Exchange> exchangeList = new ArrayList<Exchange>();
	
	public List<Exchange> getExchangeList() {
		return exchangeList;
	}

	public void setExchangeList(List<Exchange> exchangeList) {
		this.exchangeList = exchangeList;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getBidID() {
		return bidID;
	}

	public void setBidID(int bidID) {
		this.bidID = bidID;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getComputerType() {
		return computerType;
	}

	public void setComputerType(String computerType) {
		this.computerType = computerType;
	}
	
	
	
}
