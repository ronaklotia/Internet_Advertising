package com.internetadvert.edu.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.internetadvert.Validator.HashUtil;
import com.internetadvert.edu.DAO.BidDAO;
import com.internetadvert.edu.DAO.DAO;
import com.internetadvert.edu.DAO.ExchangeDAO;
import com.internetadvert.edu.DAO.InvoiceDAO;
import com.internetadvert.edu.DAO.LoginDAO;
import com.internetadvert.edu.DAO.UserDetailsDAO;
import com.internetadvert.edu.DAO.WebsiteDAO;
import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.Bid;
import com.internetadvert.edu.Pojo.Exchange;
import com.internetadvert.edu.Pojo.ProductList;
import com.internetadvert.edu.Pojo.User;
import com.internetadvert.edu.Pojo.UserDetails;
import com.internetadvert.edu.Pojo.Website;

@Controller
public class LoginController {

	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private WebsiteDAO websiteDao;

	@Autowired
	private ExchangeDAO exchangeDao;

	@Autowired
	private UserDetailsDAO userDetialsDao;

	@Autowired
	private BidDAO bidDao;

	@Autowired
	private InvoiceDAO invoiceDAO;

	@Autowired
	private ProductList productList;

	@RequestMapping(value = "/")
	public ModelAndView homePage(HttpServletRequest request,
			HttpServletResponse response) throws AdException {
		Cookie[] cookies = request.getCookies();
		String username = null;
		String password = null;
		
		System.out.println("In login logic");
		for (Cookie cookie : cookies) {
			System.out.println("cookie " + cookie.getName());
			if (cookie.getName().equals("username")) {
				username = cookie.getValue();

			} else if (cookie.getName().equals("password")) {
				password = cookie.getValue();
			}

		}
		if(username != null && password != null)
			return loginLogic(request, response, username, password, null);
		
		
		return new ModelAndView("home");

	}

	@RequestMapping(value = "/signup.htm")
	public ModelAndView signUp(HttpServletRequest request,
			HttpServletResponse response) throws AdException {

		UserDetails details = new UserDetails();

		return new ModelAndView("Signup", "details", details);

	}

	@RequestMapping(value = "/log.htm")
	public ModelAndView log(HttpServletRequest request,
			HttpServletResponse response) throws AdException {

		if (request.getSession().getAttribute("session") == null) {
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}

		User useracccSession = (User) request.getSession().getAttribute(
				"session");
		if (!useracccSession.getRole().equals("Customer")) {
			return new ModelAndView("acessDenied");
		}

		String name = request.getParameter("website");
		if (name.trim().equals("")) {
			return new ModelAndView("enterWebsite");
		}

		Website website = websiteDao.get(name);
		if (website == null) {
			return new ModelAndView("enterWebsite");
		}
		User user = (User) request.getSession().getAttribute("session");
		System.out.println(user.getUsername());

		ArrayList<Bid> bidList = bid(user, website);
		productList.setBidList(bidList);

		request.setAttribute("bid", productList);
		return new ModelAndView("CustomerLogin", "bid", productList);
	}

	@RequestMapping(value = "/welcome.htm")
	public ModelAndView welcome(HttpServletRequest request,
			HttpServletResponse response) throws AdException {

		String name = request.getParameter("website");
		System.out.println(name);
		List<Website> website = websiteDao.getAll();
		Exchange exchange = null;

		for (Website web : website) {
			if (web.getName().equals(name)) {
				exchange = web.getExchange();
				System.out.println("Exchange Name: "
						+ exchange.getExchangeName());

				request.setAttribute("web", web);
				return new ModelAndView("welcome", "web", web);
			}
		}

		return new ModelAndView("home");

	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/signin.htm")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("user") User user13,
			BindingResult bindingResult) throws AdException {
		String[] rem = new String[5];
		String username = request.getParameter("username");
		String password1 = request.getParameter("password");
		HashUtil hashUtil = new HashUtil();
		String password=hashUtil.md5(password1);
		
		System.out.println(username+password);
		if(username == null || password == null){
			return new ModelAndView("home");
		}
		if (username.trim().equals("") || password.trim().equals("")) {
			return new ModelAndView("home");
		}

		rem = request.getParameterValues("remember");


		return loginLogic(request, response, username, password, rem);
		// Website website= websiteDao.get("www.fb.com");

	}

	@SuppressWarnings("finally")
	public ModelAndView loginLogic(HttpServletRequest request,
			HttpServletResponse response, String username, String password,
			String[] rem) throws AdException {
		User user = loginDAO.get(username, password);
		if (user == null) {
			return new ModelAndView("home");
		}

		try {
			if (rem != null) {
				if (rem.length != 0) {
					Cookie uName = new Cookie("username", user.getUsername());
					Cookie uPass = new Cookie("password", user.getPassword());

					uName.setMaxAge(60 * 60 * 24);
					uPass.setMaxAge(60 * 60 * 24);

					response.addCookie(uName);
					response.addCookie(uPass);
				}
			}
		} catch (Exception e) {
			System.out.println("Ok");
		} finally {

			HttpSession httpSession = request.getSession();
			System.out.println(httpSession);
			httpSession.setAttribute("session", user);
			System.out.println(httpSession.getAttribute("session"));

			DAO.close();

			if ((user.getRole().equals("Customer"))
					&& (user.getPassword().equals(password))) {
				//
				// ArrayList<Bid> bidList=bid(user, website);
				// productList.setBidList(bidList);

				httpSession.setAttribute("sessio", user);

				request.setAttribute("bid", productList);
				return new ModelAndView("enterWebsite");

			} else if ((user.getRole().equals("Admin"))
					&& (user.getPassword().equals(password))) {
				return new ModelAndView("AdminLogin");
			} else if ((user.getRole().equals("Exchange"))
					&& (user.getPassword().equals(password))) {
				return new ModelAndView("ExchangeWorkArea");
			} else if ((user.getRole().equals("Company"))
					&& (user.getPassword().equals(password))) {

				return new ModelAndView("CompanyWorkArea");
			} else
				return new ModelAndView("home");
		}
	}

	public ArrayList<Bid> bid(User user, Website web) throws AdException {

		int count = 0;
		ArrayList<Bid> bidLt = new ArrayList<Bid>();

		Exchange exchange = web.getExchange();
		System.out.println(exchange);
		exchangeDao.getSession().update(exchange);

		UserDetails details = userDetialsDao.getAllUser(user);
		System.out.println(details);
		// UserDetails details = user.getUserDetails();

		List<Bid> bidList = exchange.getBidList();

		for (Bid bid : bidList) {

			bidDao.getSession().update(bid);

			System.out.println("inside if count");
			if ((bid.getGender().equalsIgnoreCase(details.getGender()))
					&& (bid.getComputerType().equalsIgnoreCase(
							details.getComputerType()) && (bid.getOccupation()
							.equalsIgnoreCase(details.getOccupation())))) {
				System.out.println(count);
				invoiceDAO.create(bid);
				bidLt.add(bid);
				if (count >= 2) {
					break;
				}
				count++;
			}

		}
		for (Bid b : bidList) {
			System.out.println(b.getImagepath());
		}
		return bidLt;

	}

	@RequestMapping(value = "/logout.htm")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) throws AdException {

		if (request.getSession().getAttribute("session") != null) {
			request.getSession().invalidate();
			System.out.println(request.getSession());
		}
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/invalid.htm")
	public ModelAndView invalid(HttpServletRequest request,
			HttpServletResponse response) throws AdException {

		return new ModelAndView("home");
	}

	@RequestMapping(value = "/custhome.htm")
	public ModelAndView custHome(HttpServletRequest request,
			HttpServletResponse response) throws AdException {

		return new ModelAndView("enterWebsite");
	}

}
