package com.internetadvert.edu.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.internetadvert.Validator.ExchangeValidator;
import com.internetadvert.edu.DAO.CompanyDAO;
import com.internetadvert.edu.DAO.DAO;
import com.internetadvert.edu.DAO.EmployeeDAO;
import com.internetadvert.edu.DAO.ExchangeDAO;
import com.internetadvert.edu.DAO.NetworkDAO;
import com.internetadvert.edu.DAO.UserDAO;
import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.AdverstisingCompany;
import com.internetadvert.edu.Pojo.Employee;
import com.internetadvert.edu.Pojo.Exchange;
import com.internetadvert.edu.Pojo.Network;
import com.internetadvert.edu.Pojo.NetworkList;
import com.internetadvert.edu.Pojo.User;

@Controller
public class ExchangeController{

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ExchangeDAO exchangeDAO;

	@Autowired
	private NetworkDAO networkDao;

	@Autowired
	private NetworkList networkList;
	
	@Autowired
	private EmployeeDAO employeeDao;
	
	@Autowired
	private CompanyDAO companyDao;

	@RequestMapping(value = "/exchange.htm")
	public ModelAndView exchange(HttpServletRequest request,
			HttpServletResponse response)throws AdException{

		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Admin")){
			return new ModelAndView("acessDenied");
		}
		
		Exchange exchange = new  Exchange();
		
		List<Network> network = networkDao.getAll();
		networkList.setNetwork((ArrayList<Network>) network);
		
		List<Employee> employee = employeeDao.getAll();
		networkList.setEmList((ArrayList<Employee>) employee);
		
		request.getSession().setAttribute("list1", networkList);

		return new ModelAndView("addExchange","exchange",exchange);
	}

	@RequestMapping(value = "/addExchange.htm")
	public ModelAndView addExchange(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute("exchange")Exchange exchange, BindingResult result)throws AdException{

		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Admin")){
			return new ModelAndView("acessDenied");
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name09 = request.getParameter("name");
		String emp = request.getParameter("emp");
		String net = request.getParameter("net");

		ExchangeValidator exchangeValidator = new ExchangeValidator();
		exchangeValidator.validate(exchange, result);
		
		if(result.hasErrors()){
			System.out.println("has errors loop");
			for(ObjectError error : result.getAllErrors()){
				System.out.println(error);
			}
			return new ModelAndView("addExchange");
		}
		
		Network network = networkDao.get(net);
		Employee employee = employeeDao.get(emp);
		
		AdverstisingCompany adverstisingCompany = companyDao.getByEmployee(employee);
		
		
		Exchange exc = exchangeDAO.getByEmployee(employee);
		
		if((exc != null) || (adverstisingCompany != null)){
			System.out.println("inside nuulll employee");
			return new ModelAndView("addExchange");
		}
		
		User user1 = userDAO.get(username);
		if(user1 != null){
			System.out.println("inside user loop");
			return new ModelAndView("addExchange");
		}
		
		userDAO.create(username, password, "Exchange");

		User user =   userDAO.get(username);
		System.out.println(user.getUsername());


		//		Admin admin = user.getAdmin();


		exchangeDAO.create(name09, user, network, employee,exchange);

		DAO.close();

		return new ModelAndView("addExchangeSucess");
	}

}
