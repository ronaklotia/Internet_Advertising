package com.internetadvert.edu.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.Admin;
import com.internetadvert.edu.Pojo.Network;

public class NetworkDAO extends DAO{
	
	public Network create(String name, Admin admin)
            throws AdException {
        try {
            begin();
            Network network = new Network();
            network.setName(name);
            network.setAdmin(admin);       
            List<Network> net = new ArrayList<Network>();
            net.add(network);
            
            getSession().save(network);
            commit();
            return network;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Exception while creating network: " + e.getMessage());
        }
    }
	
	 public Network get(String name)
	            throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from Network where name = :username");
	            q.setString("username", name);
	            Network network = (Network) q.uniqueResult();
	            
	            commit();
	            return network;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get network " +name, e);
	        }
	    }
	
	public List<Network> getAll()
			throws AdException {
		try {
			begin();
			List<Network> network= getSession().createQuery("from Network").list();
			
			commit();
			return network;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get netowrk ", e);
		}
	}
	
}
