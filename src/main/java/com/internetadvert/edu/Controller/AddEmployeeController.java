package com.internetadvert.edu.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.internetadvert.Validator.UserValidator;
import com.internetadvert.edu.DAO.AdminDAO;
import com.internetadvert.edu.DAO.DAO;
import com.internetadvert.edu.DAO.EmployeeDAO;
import com.internetadvert.edu.DAO.UserDAO;
import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.Admin;
import com.internetadvert.edu.Pojo.Employee;
import com.internetadvert.edu.Pojo.User;

@Controller
public class AddEmployeeController {

	@Autowired
	private UserDAO dao;
	
	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@RequestMapping(value = "/employee.htm")
	public ModelAndView employee(HttpServletRequest request,
			HttpServletResponse response)throws AdException{
		
		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User user = (User) request.getSession().getAttribute("session");
		System.out.println(user);
		if(!user.getRole().equals("Admin")){
			return new ModelAndView("acessDenied");
		}
		
		Employee employee = new Employee();
		return new ModelAndView("addEmployee","employee",employee);
	}
	
	@RequestMapping(value = "/addEmployee.htm", method = RequestMethod.POST)
	public ModelAndView addEmployee(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute("employee") Employee employee,BindingResult result)throws AdException{
		
		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Admin")){
			return new ModelAndView("acessDenied");
		}
		
		UserValidator userValidator = new UserValidator();
		userValidator.validate(employee, result);
		
		if(result.hasErrors()){
			return new ModelAndView("addEmployee");
		}
		
		String name= request.getParameter("name");
		
		
		
		User user = (User)dao.get("admin");
		
		
		
		Admin admin=adminDAO.get(user.getUserID());
	
		
		employeeDAO.create(name, admin,employee);
		
		DAO.close();
		
		return new ModelAndView("addEmployeeSucess");
	}
	
}
