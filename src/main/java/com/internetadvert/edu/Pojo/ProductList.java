package com.internetadvert.edu.Pojo;

import java.util.ArrayList;

public class ProductList {

	ArrayList<Product> productList= new ArrayList<Product>();

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}
	
	
	ArrayList<Exchange> exchangeList = new ArrayList<Exchange>();

	public ArrayList<Exchange> getExchangeList() {
		return exchangeList;
	}

	public void setExchangeList(ArrayList<Exchange> exchangeList) {
		this.exchangeList = exchangeList;
	}
	
	ArrayList<Bid> bidList = new ArrayList<Bid>();

	public ArrayList<Bid> getBidList() {
		return bidList;
	}

	public void setBidList(ArrayList<Bid> bidList) {
		this.bidList = bidList;
	}
	
	ArrayList<Invoice> invoiceList = new ArrayList<Invoice>();

	public ArrayList<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(ArrayList<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}
	
	private int totalAmount;

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
}
