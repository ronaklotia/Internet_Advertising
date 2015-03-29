package com.internetadvert.edu.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.Catogries;
import com.internetadvert.edu.Pojo.Product;

public class ProductDAO extends DAO{

	public Product create(String name, int price, Catogries catogries)
			throws AdException {
		try {
			begin();       

			Product product = new Product();
			product.setName(name);
			product.setPrice(price);
			product.setCatogries(catogries);

			getSession().save(product);
			commit();
			return product;
		} catch (HibernateException e) {
			rollback();
			//throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating network: " + e.getMessage());
		}
	}


	public Product get(String name)
			throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from Product where name = :username");
			q.setString("username",name);
			Product product =(Product) q.uniqueResult();

			commit();
			return product;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get product " + name, e);
		}
	}
	
	
	public List<Product> getAll()
			throws AdException {
		try {
			begin();
			List<Product> product= getSession().createQuery("from Product").list();
			
			commit();
			return product;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get Product ", e);
		}
	}

	
	public List<Product> getAllProd(Catogries catogries)
			throws AdException {
		try {
			begin();
			Query q= getSession().createQuery("from Product where catogries =:catogries");
			q.setParameter("catogries", catogries);
			List<Product> product = q.list();
			System.out.println(product);
			commit();
			return product;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get Product from Category ", e);
		}
	}
}
