package com.internetadvert.edu.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.internetadvert.edu.DAO.AdminDAO;
import com.internetadvert.edu.DAO.DAO;
import com.internetadvert.edu.DAO.NetworkDAO;
import com.internetadvert.edu.DAO.UserDAO;
import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.Admin;
import com.internetadvert.edu.Pojo.Network;
import com.internetadvert.edu.Pojo.NetworkList;
import com.internetadvert.edu.Pojo.ProductList;
import com.internetadvert.edu.Pojo.User;

@Controller
public class NetworkController {

	@Autowired
	private UserDAO dao;
	
	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private NetworkDAO networkDAO;
	
	@Autowired
	private NetworkList list;
	
	@RequestMapping(value = "/addnetwork.htm", method = RequestMethod.POST)
	public ModelAndView addnetwork(HttpServletRequest request,
			HttpServletResponse response)throws AdException, IOException{
		
		boolean valid= false;
		
		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			
			return new ModelAndView("acessDenied");
		}		
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Admin")){
			return new ModelAndView("acessDenied");
		}
		
		User user = (User)dao.get("admin");
		System.out.println(user.getUserID());
		
		
		Admin admin=adminDAO.get(user.getUserID());
		System.out.println(admin.getName());
		
		String location = request.getParameter("location");
		
		Network net = networkDAO.get(location);
		
		 if(net != null){
			 valid=true;
			 ArrayList<String> loc =  location();
			 list.setNetworkList(loc);
			 request.setAttribute("network", list);
			 return new ModelAndView("addNetwork","valid",valid);
		 }
		networkDAO.create(location,admin);
		DAO.close();
		
		return new ModelAndView("NetworkAdd");
	}
	
	public ArrayList<String> location(){
		ArrayList<String> loc = new ArrayList<String>();
		String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);//          
//            System.out.println("Country Code = " + obj.getCountry()
//                    + ", Country Name = " + obj.getDisplayCountry());
            loc.add(obj.getDisplayCountry() + " (" + obj.getCountry() + ")");

        }
        return loc;
	}
}
