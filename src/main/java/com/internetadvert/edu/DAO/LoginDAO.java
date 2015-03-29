package com.internetadvert.edu.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.User;

public class LoginDAO extends DAO{

	
	public User get(String username, String password)
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
}
