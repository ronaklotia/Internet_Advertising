package com.internetadvert.edu.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.internetadvert.Validator.UserValidator;
import com.internetadvert.edu.DAO.ExchangeDAO;
import com.internetadvert.edu.DAO.WebsiteDAO;
import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.Exchange;
import com.internetadvert.edu.Pojo.ProductList;
import com.internetadvert.edu.Pojo.User;
import com.internetadvert.edu.Pojo.Website;

@Controller
public class WebsiteController {

	@Autowired
	private ExchangeDAO exchangeDAO;

	@Autowired
	private ProductList p;

	@Autowired
	WebsiteDAO websiteDAO;

	@RequestMapping(value = "/website.htm")
	public ModelAndView website(HttpServletRequest request,
			HttpServletResponse response)throws AdException{
		
		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Admin")){
			return new ModelAndView("acessDenied");
		}

		List<Exchange> exc = exchangeDAO.getAll();

		p.setExchangeList((ArrayList<Exchange>) exc);
		Website website = new Website();
		request.getSession().setAttribute("exc", p);
		return new ModelAndView("addWebsite","website",website);
	}


	@RequestMapping(value = "/addWebsite.htm")
	public ModelAndView addWebsite(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute("website") Website website, BindingResult result)throws AdException{
		
		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Admin")){
			return new ModelAndView("acessDenied");
		}
		
	
		String name= request.getParameter("name");
		String exc = request.getParameter("exch");
		Exchange exchange = exchangeDAO.get(exc);
		System.out.println(name);
		
		if(name.trim().equals("")){
			return new ModelAndView("addWebsite");
		}
		
		Website wen = websiteDAO.get(name);
		
		if(wen!=null){
		
			System.out.println("Inside webiste not empty");
			result.rejectValue("name", "name.duplicate");
		}
		
		if(result.hasErrors()){
			for(ObjectError e:result.getAllErrors()){
				System.out.println(e);
			}
			System.out.println("inside result");
			return new ModelAndView("addWebsite");
		}
		
		

		
		websiteDAO.create(name, exchange,website);


		return new ModelAndView("addWebsiteSucess");
	}

	@RequestMapping(value="isWeb.htm",method=RequestMethod.GET)	
	public void doGet(HttpServletRequest request, 
			HttpServletResponse response)
					throws IOException, ServletException, AdException 
	{

		
		String website = String.valueOf(request.getParameter("web"));
		System.out.println(website);

		int websiteCheck = validWebsite(website);

		if(websiteCheck == 1)
		{
			System.out.println("1=true");
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("true");
			
		}
		else
		{
			System.out.println("0=false");
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("false");
			
		}


	}


	public int validWebsite(String web) throws AdException{

		Website website= websiteDAO.get(web);
		if(website == null){
			return 1;
		}


		return 0;
	}

}
