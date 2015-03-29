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
@Table(name="Product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PRODUCT_ID", unique=true, nullable=false)
	private int productID;
	
	@Column(name="PRODUCT_NAME", unique=true, nullable=false, length=30)
	private String name;
	
	@Column(name="PRICE", nullable=false, length=30)
	private int price;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="categoryID", nullable=false)
	private Catogries catogries;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="product", cascade=CascadeType.ALL)
	private List<Bid> bidList = new ArrayList<Bid>();
	
	public List<Bid> getBidList() {
		return bidList;
	}

	public void setBidList(List<Bid> bidList) {
		this.bidList = bidList;
	}

	public Catogries getCatogries() {
		return catogries;
	}

	public void setCatogries(Catogries catogries) {
		this.catogries = catogries;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
