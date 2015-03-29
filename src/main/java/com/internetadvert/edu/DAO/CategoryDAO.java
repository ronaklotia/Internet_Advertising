package com.internetadvert.edu.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.AdverstisingCompany;
import com.internetadvert.edu.Pojo.Catogries;

public class CategoryDAO extends DAO{


	public Catogries create(String name, AdverstisingCompany company)
			throws AdException {
		try {
			begin();       

			Catogries catogries = new Catogries();
			catogries.setCategoryName(name);
			catogries.setCompany(company);

			getSession().save(catogries);
			commit();
			return catogries;
		} catch (HibernateException e) {
			rollback();
			//throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating network: " + e.getMessage());
		}
	}

	 public Catogries get(String name)
	            throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from Catogries where categoryName = :username");
	            q.setString("username",name);
	            Catogries user =(Catogries) q.uniqueResult();
	           System.out.println( user.getCategoryName());
	            commit();
	            return user;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get user " + name, e);
	        }
	    }
	
	public List<Catogries> getAll()
			throws AdException {
		try {
			begin();
			List<Catogries> catogries= getSession().createQuery("from Catogries").list();
			
			commit();
			return catogries;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get category ", e);
		}
	}
	
	public List<Catogries> getA(AdverstisingCompany company)
			throws AdException {
		try {
			begin();
			Query q= getSession().createQuery("from Catogries where company =:comapany");
			q.setParameter("comapany", company);
			
			List<Catogries> catogries = q.list();
			System.out.println(catogries);
			commit();
			return catogries;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get category ", e);
		}
	}
	
}
