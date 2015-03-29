package com.internetadvert.edu.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.internetadvert.Validator.HashUtil;
import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.User;


public class UserDAO extends DAO{

	public UserDAO() {
	}
	
	
	

	public User get(String username)
			throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username");
			q.setString("username", username);
			User user = (User) q.uniqueResult();
			
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get user " + username, e);
		}
	}

	public User create(String username, String password, String role)
			throws AdException {
		try {
			begin();
			//	            List<User> userList = getSession().createQuery("from User where role =:role").list();
			//	            if(!userList.isEmpty()){
			//	            for(User user : userList){
			//	            	if(user.getRole().equals("Admin")){
			//	            		System.out.println(user.getUsername());
			//	            		getSession().save(user);
			//	    	            commit();
			//	            		return user;
			//	            	}
			//	            }
			//	            }

			User user = new User();
			
			HashUtil hashUtil = new HashUtil();
			
			String salt = "Random$SaltValue#WithSpecialCharacters12@$@4&#%^$*";
			String password1 = hashUtil.md5(password);
			System.out.println(password1);
			user.setRole(role);
			user.setPassword(password1);
			user.setUsername(username);
			
			getSession().save(user);

			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			//throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating user: " + e.getMessage());
		}
	}


}
