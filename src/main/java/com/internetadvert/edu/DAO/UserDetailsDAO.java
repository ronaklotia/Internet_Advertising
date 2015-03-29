package com.internetadvert.edu.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.User;
import com.internetadvert.edu.Pojo.UserDetails;

public class UserDetailsDAO extends DAO{

	 public UserDetailsDAO() {
	    }
	 
	 public UserDetails get(int userID)
	            throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from UserDetails where userID = :userID");
	            System.out.println(q);
	            q.setInteger("userID", userID);
	            UserDetails user = (UserDetails) q.uniqueResult();
	            commit();
	            return user;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get user " + userID, e);
	        }
	    }
	 
	 public UserDetails getByEmail(String email)
	            throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from UserDetails where email = :userID");
	            q.setString("userID",email);
	            UserDetails user = (UserDetails) q.uniqueResult();
	            commit();
	            return user;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get user by Email " + email, e);
	        }
	    }
	 
	 
	 public UserDetails getAllUser(User user)
	            throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from UserDetails where user = :userID");
	            System.out.println(q);
	            q.setParameter("userID", user);
	            UserDetails userdetails = (UserDetails) q.uniqueResult();
	            commit();
	            return userdetails;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get user " + user, e);
	        }
	    }
	 
	   public UserDetails create(String name, String phoneNO, String occupation, String gender,String computerTyp, String email, User user,UserDetails userdetails)
	            throws AdException {
	        try {
	            begin();
//	            UserDetails userdetails = new UserDetails();
//	            userdetails.setComputerType(computerTyp);
//	            userdetails.setEmail(email);
//	            userdetails.setGender(gender);
//	            userdetails.setName(name);
//	            userdetails.setOccupation(occupation);
//	            userdetails.setPhoneNo(phoneNO);
	            System.out.println("Inside create userDetails");
	            userdetails.setUser(user);
	            user.setUserDetails(userdetails);
	            
	            getSession().save(userdetails);
	            commit();
	            return userdetails;
	        } catch (HibernateException e) {
	            rollback();
	            //throw new AdException("Could not create user " + username, e);
	            throw new AdException("Exception while creating userdetails: " + e.getMessage());
	        }
	    }
	
}
