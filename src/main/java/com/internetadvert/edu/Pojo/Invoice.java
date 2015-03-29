package com.internetadvert.edu.Pojo;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@Entity
@FilterDef(name = "statusfilter", parameters = @ParamDef(name = "statusFilterID", type = "java.lang.String"))
@Table(name="Invoice")
@Filter(name = "statusfilter", condition = "status =:statusFilterID")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="INVOICE_ID", unique=true, nullable=false)
	private int invoiceId;
	
	@Column(name="STATUS", nullable=false)
	private String status;
	
	
	@Column(name="AMOUNT", nullable=false)
	private int amount;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="bidID", nullable=false)
	private Bid bid;
	
	@Column(name="Date", nullable=false)
	private Date date;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "invoice", cascade = CascadeType.ALL)
	private Payment payment;
	
	
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Bid getBid() {
		return bid;
	}
	public void setBid(Bid bid) {
		this.bid = bid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}


}
