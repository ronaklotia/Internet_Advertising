package com.internetadvert.edu.DAO;

import java.util.Date;

import org.hibernate.HibernateException;

import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.Invoice;
import com.internetadvert.edu.Pojo.Payment;

public class PaymentDAO extends DAO{

	public Payment create(Invoice invoice, int amount)
            throws AdException {
        try {
            begin();
            Date date = new Date();
            Payment payment = new Payment();
            payment.setAmount(amount);
            payment.setDate(date);
            payment.setInvoice(invoice);
            invoice.setStatus("Paid");
            invoice.setPayment(payment);
            
            getSession().save(payment);
            commit();
            return payment;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Exception while creating Payment: " + e.getMessage());
        }
    }
	
}
