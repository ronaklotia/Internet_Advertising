package com.internetadvert.edu.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.Employee;
import com.internetadvert.edu.Pojo.Exchange;
import com.internetadvert.edu.Pojo.Network;
import com.internetadvert.edu.Pojo.User;

@Service
@Configurable
public class ExchangeDAO extends DAO{

	

	public Exchange create(String name, User user, Network network, Employee employee,Exchange exchange)
			throws AdException {
		try {
			begin();       

//			Exchange exchange = new Exchange();
//			exchange.setExchangeName(name);
			exchange.setUser(user);
			exchange.setNetwork(network);
			exchange.setEmployee(employee);

			user.setExchange(exchange);
			System.out.println(user.getExchange());

			getSession().save(exchange);
			commit();
			return exchange;
		} catch (HibernateException e) {
			rollback();

			throw new AdException("Exception while creating network: " + e.getMessage());
		}
	}

	public List<Exchange> getAll()
			throws AdException {
		try {
			begin();
			List<Exchange> exchange= getSession().createQuery("from Exchange").list();

			commit();
			return exchange;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get exchange ", e);
		}
	}

	public Exchange get(String name)
			throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from Exchange where exchangeName = :username");
			q.setString("username",name);
			Exchange exchange =(Exchange) q.uniqueResult();

			commit();
			return exchange;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get exchange" + name, e);
		}
	}
	
	
	public Exchange getByUser(User user)
			throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from Exchange where user = :username");
			q.setParameter("username",user);
			Exchange exchange =(Exchange) q.uniqueResult();

			commit();
			return exchange;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get exchange by user" + user, e);
		}
	}

	
	public Exchange getByEmployee(Employee employee)
			throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from Exchange where employee =:id");
			q.setParameter("id", employee);

			Exchange exc = (Exchange)q.uniqueResult();
			commit();
			return exc;
		}catch(HibernateException e){
			rollback();
			throw new AdException("Could not get Exchange Employee" + employee, e);
		}


	}
	
	
}
