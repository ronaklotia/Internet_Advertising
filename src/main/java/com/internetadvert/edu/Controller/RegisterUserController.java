package com.internetadvert.edu.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.internetadvert.edu.DAO.DAO;
import com.internetadvert.edu.DAO.UserDAO;
import com.internetadvert.edu.DAO.UserDetailsDAO;
import com.internetadvert.edu.Pojo.Email;
import com.internetadvert.edu.Pojo.User;
import com.internetadvert.edu.Pojo.UserDetails;


@Controller
public class RegisterUserController {

	@Autowired
	UserDAO userDao;
	
	@Autowired
	private UserDetailsDAO userDetailsDAO;
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public ModelAndView loginDetails(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("username") String username, @RequestParam("pwd") String password,
			@RequestParam("Role") String role,@ModelAttribute("details") UserDetails details1, BindingResult result) throws Exception{

		

		String name = request.getParameter("user_name");
		String phoneNO = request.getParameter("phoneNo");
		String occupation = request.getParameter("occupation");
		String gender = request.getParameter("gender");
		String computerTyp123 = request.getParameter("type123");
		String email = request.getParameter("email");
		
		System.out.println(computerTyp123);
		
		User user1 = userDao.get(username);
		if(user1 != null){
			System.out.println("inside user not null");
			return new ModelAndView("Signup");
		}		
		UserDetails ud = userDetailsDAO.getByEmail(email);
		
		if(ud != null){		
			System.out.println("inside email not null");
			result.rejectValue("email", "required.email");
		}		 
		if(result.hasErrors()){
			System.out.println("inside has errors not null");
			return new ModelAndView("Signup");
		}
			
		
		userDao.create(username, password,role);
		
		User user =   userDao.get(username);
		
		request.getSession().setAttribute("session", user);
		
		
		userDetailsDAO.create(name, phoneNO, occupation, gender, computerTyp123, email, user,details1);
		
//		UserDetails details = userDetailsDAO.get(user.getUserID());
//		user.setUserDetails(details);
		
		Email emai = new Email();
		emai.sendemail(email, username);

		DAO.close();

		return new ModelAndView("enterWebsite");
	}

}
