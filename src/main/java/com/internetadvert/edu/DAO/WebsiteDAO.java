package com.internetadvert.edu.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.Exchange;
import com.internetadvert.edu.Pojo.Website;

public class WebsiteDAO extends DAO{

	
	
	public Website create(String name, Exchange exchange, Website website)
			throws AdException {
		try {
			begin();       
			
//			Website website = new Website();
//			website.setName(name);
			website.setExchange(exchange);
			
			getSession().save(website);
			commit();
			return website;
		} catch (HibernateException e) {
			rollback();
			
			throw new AdException("Exception while creating Website: " + e.getMessage());
		}
	}
	
	 public Website get(String name)
	            throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from Website where name = :name");
	            q.setString("name", name);
	            Website website = (Website) q.uniqueResult();
	           
	            commit();
	            return website;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get user " + name, e);
	        }
	    }

	 public List<Website> getAll()
	            throws AdException {
	        try {
	            begin();
	            List<Website> website = getSession().createQuery("from Website").list();
	            	           
	            commit();
	            return website;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get website", e);
	        }
	    }
}
