package com.internetadvert.edu.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.Admin;
import com.internetadvert.edu.Pojo.Employee;

public class EmployeeDAO extends DAO{

	public Employee create(String name, Admin admin, Employee employee)
            throws AdException {
        try {
            begin();
//            Employee employee = new Employee();
//            employee.setName(name);
            employee.setAdmin(admin);
            
            getSession().save(employee);
            commit();
            return employee;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Exception while creating network: " + e.getMessage());
        }
    }
	
	 public Employee get(String name)
	            throws AdException {
	        try {
	            begin();
	            Query q = getSession().createQuery("from Employee where name = :username");
	            q.setString("username", name);
	            Employee employee = (Employee) q.uniqueResult();
	           
	            commit();
	            return employee;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdException("Could not get employee " + name, e);
	        }
	    }
	 
	
	public List<Employee> getAll()
			throws AdException {
		try {
			begin();
			List<Employee> employee= getSession().createQuery("from Employee").list();
			
			commit();
			return employee;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get employee ", e);
		}
	}
}
