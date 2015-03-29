package com.internetadvert.edu.Controller;

import java.beans.DesignMode;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.internetadvert.edu.DAO.BidDAO;
import com.internetadvert.edu.DAO.CategoryDAO;
import com.internetadvert.edu.DAO.CompanyDAO;
import com.internetadvert.edu.DAO.ExchangeDAO;
import com.internetadvert.edu.DAO.InvoiceDAO;
import com.internetadvert.edu.DAO.ProductDAO;
import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.AdverstisingCompany;
import com.internetadvert.edu.Pojo.Bid;
import com.internetadvert.edu.Pojo.Catogries;
import com.internetadvert.edu.Pojo.Exchange;
import com.internetadvert.edu.Pojo.Image;
import com.internetadvert.edu.Pojo.Invoice;
import com.internetadvert.edu.Pojo.Product;
import com.internetadvert.edu.Pojo.ProductList;
import com.internetadvert.edu.Pojo.User;

@Controller
public class BidController {

	@Autowired
	private ExchangeDAO exchangeDao;
	
	@Autowired
	private ProductList productList;
	
	@Autowired
	private InvoiceDAO invoiceDao;
	
	@Autowired
	BidDAO bidDAO ;
	
	@Autowired
	ProductDAO productDAO;

	@Autowired
	ExchangeDAO exchangeDAO;
	
	@Autowired
	CompanyDAO companyDao;
	
	@Autowired
	CategoryDAO categoryDao;
	
	@RequestMapping(value = "/deleteBid.htm")
	public ModelAndView deleteBid(HttpServletRequest request,
			HttpServletResponse response)throws AdException{
	
		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Exchange")){
			return new ModelAndView("acessDenied");
		}
		
		return new ModelAndView("deleteBid");
	}
	
	
	@RequestMapping(value = "/bid.htm")
	public ModelAndView bid(HttpServletRequest request,
			HttpServletResponse response)throws AdException{
		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Company")){
			return new ModelAndView("acessDenied");
		}
		
		ProductList productList = new ProductList();
		
		AdverstisingCompany adverstisingCompany = companyDao.getInt(useracccSession);
		List<Catogries> catogries = categoryDao.getA(adverstisingCompany);
		for(Catogries c : catogries){
			List<Product> p = productDAO.getAllProd(c);
			for(Product pro : p){
				productList.getProductList().add(pro);
			}
		}
//		List<Product> product= productDAO.getAll();
//		productList.setProductList((ArrayList<Product>) product);


		List<Exchange> exchangeList= exchangeDAO.getAll();
		productList.setExchangeList((ArrayList<Exchange>) exchangeList);


		request.getSession().setAttribute("list4", productList);
		return new ModelAndView("addBid","list",productList);
	}
	
	
	
	@RequestMapping(value = "/viewInvoice.htm")
	public ModelAndView invoice(HttpServletRequest request,
			HttpServletResponse response)throws AdException{
		
		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Exchange")){
			return new ModelAndView("acessDenied");
		}
		
		return new ModelAndView("viewInvoice");
	}
	
	
	@RequestMapping(value = "/filterInvoice.htm")
	public ModelAndView invoiceFilter(HttpServletRequest request,
			HttpServletResponse response)throws AdException{
		
		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Exchange")){
			return new ModelAndView("acessDenied");
		}
		
		User user = (User)request.getSession().getAttribute("session");
		
		Exchange exchange = exchangeDao.getByUser(user);
		System.out.println(exchange.getExchangeName());
		List<Bid> bidList= exchange.getBidList();
		
		ArrayList<Invoice> list = new ArrayList<Invoice>();
		
		for(Bid bid: bidList){
			System.out.println("Bid inside for: "+bid);
			ArrayList<Invoice> inv = (ArrayList<Invoice>) invoiceDao.getAllInvoice(bid);
			
			for(Invoice invoice1 : inv){
				Invoice invoice = invoiceDao.unPaidInvocie(invoice1);
				list.add(invoice);
			}		
		
		}
		
		productList.setInvoiceList(list);
		
		request.setAttribute("status", productList);
		
		return new ModelAndView("unpaidInvoice","status",productList);
	}
	
	
	@RequestMapping(value = "/addBid.htm")
	public ModelAndView addBid(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("image") Image image, BindingResult result)throws AdException, Exception, IOException{

		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Company")){
			return new ModelAndView("acessDenied");
		}
		
		String occupation = request.getParameter("occupation");
		String gender= request.getParameter("gender");
		String age = request.getParameter("age");
		String compType = request.getParameter("compType");
		String product = request.getParameter("product");
		String exchange = request.getParameter("Exchange");
		String ad1 = request.getParameter("adtype");
		String pric = request.getParameter("price");
		Product pro = productDAO.get(product);
		int price =Integer.parseInt(pric);

		if(occupation.trim().equals("")|| gender.trim().equals("") || age.trim().equals("") || compType.trim().equals("") || ad1.trim().equals("")){
			return new ModelAndView("addBid");
		}
			
		
		
		CommonsMultipartFile multiPartFile = image.getImage();
		File destination = new File("C:\\Users\\Avi\\Documents\\workspace-sts-3.5.0.RELEASE\\Internet_Advertising\\src\\main\\webapp\\resources\\", multiPartFile.getOriginalFilename());
		multiPartFile.transferTo(destination);
		
		
		
		String inamge_name = destination.getAbsolutePath().substring(destination.getAbsolutePath().lastIndexOf("\\")+1);
		
		String file = destination.getAbsolutePath();
		
		
		
		System.out.println(file);
		Exchange exc = exchangeDAO.get(exchange);
		System.out.println(exc.getExchangeName());



		bidDAO.create(occupation, gender, compType, age, pro, exc, ad1,price,file,inamge_name);



		//Exchange



		return new ModelAndView("addBidSucess");
	}
}
