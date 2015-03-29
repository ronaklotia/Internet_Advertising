package com.internetadvert.edu.Controller;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.NetworkList;
import com.internetadvert.edu.Pojo.User;

@Controller
public class DisplayNetworkController {

	@Autowired
	NetworkList net;
	
	@RequestMapping(value = "/network.htm")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response)throws AdException{
		
		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Admin")){
			return new ModelAndView("acessDenied");
		}
		net.setNetworkList(location());
		
		request.setAttribute("network",net);
		return new ModelAndView("addNetwork","network",net);
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
