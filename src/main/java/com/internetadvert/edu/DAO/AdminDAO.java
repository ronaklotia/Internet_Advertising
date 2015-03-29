package com.internetadvert.edu.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.Admin;
import com.internetadvert.edu.Pojo.User;

public class AdminDAO extends DAO{

	private static AdminDAO adminDAO;

	public AdminDAO(){

	}

	public static AdminDAO getInstance(){
		if (adminDAO == null){
			adminDAO = new AdminDAO();
		}
		return adminDAO;

	}
	
	
	public Admin create(User user1)
			throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from Admin");
			Admin admin;
			if(q.list().isEmpty()){
				admin = new Admin();
				admin.setName("admin");
				admin.setUser1(user1);
				user1.setAdmin(admin);
				getSession().save(admin);
				commit();
				return admin;
			}
			else			
			return null;
		} catch (HibernateException e) {
			rollback();
			//throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		}
	}
	
	
	 public Admin get(int id)
	            throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from Admin where adminID = :username");
	            q.setInteger("username", id);
	            Admin admin = (Admin) q.uniqueResult();
	            System.out.println(admin.getName());
	            commit();
	            return admin;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get admin " + id, e);
	        }
	    }


}
