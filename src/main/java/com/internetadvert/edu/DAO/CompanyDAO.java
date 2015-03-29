package com.internetadvert.edu.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.AdverstisingCompany;
import com.internetadvert.edu.Pojo.Employee;
import com.internetadvert.edu.Pojo.Network;
import com.internetadvert.edu.Pojo.User;

public class CompanyDAO extends DAO{

	public AdverstisingCompany create(String name, User user, Network network,Employee employee,AdverstisingCompany company)
			throws AdException {
		try {
			begin();       

//			AdverstisingCompany company = new AdverstisingCompany();
//
//			company.setName(name);            
			company.setUser(user);   
			company.setNetwork(network);
			company.setEmployee(employee);
			user.setAdverstisingCompany(company);   
			employee.setAdverstisingCompany(company);

			getSession().save(company);
			commit();
			return company;

		} catch (HibernateException e) {
			rollback();
			//throw new AdException("Could not create user " + username, e);
			throw new AdException("Exception while creating company: " + e.getMessage());
		}
	}


	//	 public AdverstisingCompany get(String username)
	//	            throws AdException {
	//	        try {
	//	            begin();
	//	            Query q = getSession().createQuery("from AdverstisingCompany where username = :username");
	//	            q.setString("username", username);
	//	            AdverstisingCompany company =(AdverstisingCompany)q.uniqueResult();
	//	            
	//	            commit();
	//	            return company;
	//	        } catch (HibernateException e) {
	//	            rollback();
	//	            throw new AdException("Could not get user " + username, e);
	//	        }
	//	    }



	public AdverstisingCompany getInt(User user)
			throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from AdverstisingCompany where user =:id");
			q.setParameter("id", user);

			AdverstisingCompany adverstisingCompany = (AdverstisingCompany)q.uniqueResult();
			commit();
			return adverstisingCompany;
		}catch(HibernateException e){
			rollback();
			throw new AdException("Could not get Company" + user, e);
		}


	}

	
	public AdverstisingCompany getByEmployee(Employee employee)
			throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from AdverstisingCompany where employee =:id");
			q.setParameter("id", employee);
			
			AdverstisingCompany adverstisingCompany = (AdverstisingCompany)q.uniqueResult();
			
//			System.out.println("inside getEmployee"+adverstisingCompany.getName());
			commit();
			return adverstisingCompany;
		}catch(HibernateException e){
			rollback();
			throw new AdException("Could not get Company Employee" + employee, e);
		}


	}

}

