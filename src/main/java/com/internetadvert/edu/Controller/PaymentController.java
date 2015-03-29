package com.internetadvert.edu.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.internetadvert.edu.DAO.BidDAO;
import com.internetadvert.edu.DAO.CategoryDAO;
import com.internetadvert.edu.DAO.CompanyDAO;
import com.internetadvert.edu.DAO.InvoiceDAO;
import com.internetadvert.edu.DAO.PaymentDAO;
import com.internetadvert.edu.DAO.ProductDAO;
import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.AdverstisingCompany;
import com.internetadvert.edu.Pojo.Bid;
import com.internetadvert.edu.Pojo.Catogries;
import com.internetadvert.edu.Pojo.Invoice;
import com.internetadvert.edu.Pojo.Payment;
import com.internetadvert.edu.Pojo.Product;
import com.internetadvert.edu.Pojo.ProductList;
import com.internetadvert.edu.Pojo.User;

@Controller
public class PaymentController {

	@Autowired
	private ProductList productList;
	
	@Autowired
	private CompanyDAO companyDao;
	
	@Autowired
	private CategoryDAO categoryDao;
	
	@Autowired
	private ProductDAO productDao;
	
	@Autowired
	private BidDAO bidDao;
	
	@Autowired
	private InvoiceDAO invoiceDao;
	
	@Autowired
	private PaymentDAO paymentDao;
	
	@RequestMapping(value = "/payment.htm")
	public ModelAndView log(HttpServletRequest request,
			HttpServletResponse response)throws AdException{
		
		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Company")){
			return new ModelAndView("acessDenied");
		}
		
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("session");
		
		
		
		AdverstisingCompany adverstisingCompany= companyDao.getInt(user);
		
		ArrayList<Catogries> catogries = (ArrayList<Catogries>) categoryDao.getA(adverstisingCompany);
		
		ArrayList<Product> product=new ArrayList<Product>();
		
		for(Catogries catogries2 : catogries){
			ArrayList<Product> pro =(ArrayList<Product>) productDao.getAllProd(catogries2);
			for(Product p : pro){
				product.add(p);
			}
		}
		
		ArrayList<Bid> bidList = new ArrayList<Bid>();
		
		for(Product pro : product){
			ArrayList<Bid> bid = (ArrayList<Bid>) bidDao.getAllBid(pro);
			for(Bid bidlis : bid){
				bidList.add(bidlis);
			}
		}
		
		ArrayList<Invoice> invoice = new ArrayList<Invoice>();
		for(Bid bid : bidList){
			ArrayList<Invoice> inv = (ArrayList<Invoice>) invoiceDao.getAllInvoice(bid);
			for(Invoice in : inv){
				if(in.getStatus().equals("Pending"))
				invoice.add(in);
			}
		}
		System.out.println("InoviceList"+invoice);


		productList.setInvoiceList(invoice);
		request.getSession().setAttribute("list3",productList);
		
		return new ModelAndView("payment","list",productList);
	}
	
	
	@RequestMapping(value = "/makePayment.htm")
	public ModelAndView paymentRedirect(HttpServletRequest request,
			HttpServletResponse response)throws AdException, IOException{
		
		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getAttribute("session");
		if(!useracccSession.getRole().equals("Company")){
			return new ModelAndView("acessDenied");
		}
		
		System.out.println(request.getScheme());
		
		if (request.getScheme().equalsIgnoreCase("http")) {
			 String origURL = request.getRequestURL().toString();
			 System.out.println(origURL);
			 String newURL = httpsURL(origURL);
			 String formData = request.getQueryString();
			 System.out.println(newURL);
			 if (formData != null) {
			 newURL = newURL + "?" + formData;
			 }
			
			 return new ModelAndView("redirect:paymentRedirect.htm");
//			 return new ModelAndView(newURL,"list",productList);
			 } else {
			 String currentURL = request.getRequestURL().toString();
			 String formData = request.getQueryString();
			 PrintWriter out = response.getWriter();
			 String docType =
			 "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
			 "Transitional//EN\">\n";
			 String title = "Security Info";
			 
			 boolean isSecure = request.isSecure();
			 if (isSecure) {
			 String keyAttribute =
			 "javax.servlet.request.key_size";
			 // Available only with servlets 2.3
			 Integer keySize =
			 (Integer)request.getAttribute(keyAttribute);
			 String sizeString =
			 replaceNull(keySize, "Unknown");
			 String cipherAttribute =
			 "javax.servlet.request.cipher_suite";
			 // Available only with servlets 2.3
			 String cipherSuite =
			 (String)request.getAttribute(cipherAttribute);
			 String cipherString =
			 replaceNull(cipherSuite, "Unknown");
			 String certAttribute =
			 "javax.servlet.request.X509Certificate";
			 // Available with servlets 2.2 and 2.3
			 X509Certificate certificate =
			 (X509Certificate)request.getAttribute(certAttribute);
			 String certificateString =
			 replaceNull(certificate, "None");
			 }
			 } 
		return null;
	}
	
	@RequestMapping(value = "/paymentSucess.htm")
	public ModelAndView paymentSucess(HttpServletRequest request,
			HttpServletResponse response)throws AdException, IOException{
		
		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Company")){
			return new ModelAndView("acessDenied");
		}
		
		ArrayList<Invoice> invo =  (ArrayList<Invoice>) request.getSession().getAttribute("inv");
		for(Invoice invoice : invo){
		paymentDao.create(invoice, invoice.getAmount());
		invoiceDao.updateStatus(invoice);
		}
		
		
		
		return new ModelAndView("makePaymentSucess");
	}
	
	
	@RequestMapping(value = "/paymentRedirect.htm")
	public ModelAndView payment(HttpServletRequest request,
			HttpServletResponse response)throws AdException, IOException{
		
		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		ArrayList<Invoice> inc = new ArrayList<Invoice>();
		String[] pay = request.getParameterValues("pay");
		
		if(pay==null){
			return new ModelAndView("payment");
		}
		
		int total = 0;
		for(String inv : pay){
			int i = Integer.parseInt(inv);
			Invoice invoice = invoiceDao.getByID(i);
			inc.add(invoice);
			
			total+= invoice.getAmount();
		}
		System.out.println(total);
		productList.setTotalAmount(total);
		HttpSession ses = request.getSession();
		ses.setAttribute("inv", inc);
		request.setAttribute("list", productList);
		
		
		 return new ModelAndView("makePayment","list",productList);
		
	}
	
	private String httpsURL(String origURL) {
		 int index = origURL.indexOf(":");
		 StringBuffer newURL = new StringBuffer(origURL);
		 newURL.insert(index, 's');
		 return(newURL.toString());
		 }
	
	private String replaceNull(Object obj, String fallback) {
		 if (obj == null) {
		 return(fallback);
		 } else {
		 return(obj.toString());
		 }
		 }
	
}
