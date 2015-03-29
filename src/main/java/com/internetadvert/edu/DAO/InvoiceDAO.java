package com.internetadvert.edu.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.Bid;
import com.internetadvert.edu.Pojo.Invoice;

public class InvoiceDAO extends DAO{

	public Invoice create(Bid bid)
            throws AdException {
        try {
            begin();
            Date date = new Date();
            Invoice invoice  = new Invoice();
            invoice.setAmount(bid.getPrice());
            invoice.setStatus("Pending");
            invoice.setBid(bid);
            invoice.setDate(date);
           
            getSession().save(invoice);
            commit();
            return invoice;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Exception while creating Inoicve: " + e.getMessage());
        }
    }
	
	
	public void updateStatus(Invoice invoice) throws AdException {
		try{
			begin();
			int id = invoice.getInvoiceId();
			String status = "Paid";
			Query q = getSession().createQuery("update Invoice set status =:status where id =:id");
			q.setParameter("status",status);
			q.setParameter("id", id);
			
			q.executeUpdate();			
			commit();
		}catch(HibernateException e){
			rollback();
			throw new AdException("Exception while updating Inoicve: " + e.getMessage());
		}
	}
	
	public List<Invoice> getAllInvoice(Bid bid)
			throws AdException {
		try {
			begin();
			Query q= getSession().createQuery("from Invoice where bid =:bid");
			q.setParameter("bid", bid);
			List<Invoice> invoiceList = q.list();
			System.out.println("List of Invoice "+q.list());
			commit();
			return invoiceList;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get Invoice from bid ", e);
		}
	}
	
	public Invoice getByID(int id)
			throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from Invoice where  invoiceId= :id");
			q.setInteger("id",id);
			Invoice invoice = (Invoice) q.uniqueResult();
			
			commit();
			return invoice;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get Invoice by id " + id, e);
		}
	}
	
	
	public Invoice getByStatus(String status)
			throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from Invoice where  status= :id");
			q.setString("id",status);
			Invoice invoice = (Invoice) q.uniqueResult();
			
			commit();
			return invoice;
		} catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get Invoice by status" +status, e);
		}
	}
	
	
	public Invoice unPaidInvocie(Invoice invoice) throws AdException{
		try{
		
		
		
		Filter filter = getSession().enableFilter("statusfilter");
		filter.setParameter("statusFilterID","Pending");
		
		
		Invoice invoice1 = getByID(invoice.getInvoiceId());
		
		
		return invoice1;
		}
	catch (HibernateException e) {
		rollback();
		throw new AdException("Could not get Invoice by list ", e);
	}
	}
}
