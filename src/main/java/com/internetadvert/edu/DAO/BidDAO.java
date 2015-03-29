package com.internetadvert.edu.DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.Bid;
import com.internetadvert.edu.Pojo.Exchange;
import com.internetadvert.edu.Pojo.Product;

public class BidDAO extends DAO{

	public Bid create(String occupation, String gender, String compType, String age, Product product, Exchange exc, String ad1, int price,String path,String imagename)
			throws AdException, IllegalStateException, IOException {
		try {
			begin();       
			Bid bid =new Bid();
			bid.setAge(age);
			bid.setComputerType(compType);
			bid.setOccupation(occupation);
			bid.setGender(gender);
			bid.setProduct(product);
			bid.setAd1(ad1);
			bid.setPrice(price);
			bid.setImagepath(path);
			bid.setName(imagename);
			
			ArrayList<Exchange> exchangeList = new ArrayList<Exchange>();
			exchangeList.add(exc);
			
			bid.setExchangeList(exchangeList);
			
			ArrayList<Bid> bidList = new ArrayList<Bid>();
			bidList.add(bid);			
			exc.setBidList(bidList);
			
			getSession().save(bid);
			commit();
			return bid;
		} catch (HibernateException e) {
			rollback();
			//throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating Bid: " + e.getMessage());
		}
	}
	
	
	public List<Bid> getAllBid(Product product)
			throws AdException {
		try {
			begin();
			Query q= getSession().createQuery("from Bid where product =:product");
			q.setParameter("product", product);
			List<Bid> bid = q.list();
			System.out.println(product);
			commit();
			return bid;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get Bid from product ", e);
		}
	}
	
}
