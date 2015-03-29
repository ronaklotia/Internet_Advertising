package com.internetadvert.edu.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.internetadvert.Validator.ComapnyValidator;
import com.internetadvert.edu.DAO.BidDAO;
import com.internetadvert.edu.DAO.CategoryDAO;
import com.internetadvert.edu.DAO.CompanyDAO;
import com.internetadvert.edu.DAO.DAO;
import com.internetadvert.edu.DAO.EmployeeDAO;
import com.internetadvert.edu.DAO.ExchangeDAO;
import com.internetadvert.edu.DAO.NetworkDAO;
import com.internetadvert.edu.DAO.ProductDAO;
import com.internetadvert.edu.DAO.UserDAO;
import com.internetadvert.edu.Exception.AdException;
import com.internetadvert.edu.Pojo.AdverstisingCompany;
import com.internetadvert.edu.Pojo.CategoryList;
import com.internetadvert.edu.Pojo.Catogries;
import com.internetadvert.edu.Pojo.Employee;
import com.internetadvert.edu.Pojo.Exchange;
import com.internetadvert.edu.Pojo.Network;
import com.internetadvert.edu.Pojo.NetworkList;
import com.internetadvert.edu.Pojo.Product;
import com.internetadvert.edu.Pojo.ProductList;
import com.internetadvert.edu.Pojo.User;
import com.internetadvert.edu.Pojo.Website;

@Controller
public class CompanyController {

	@Autowired
	ProductDAO productDAO;

	@Autowired
	ExchangeDAO exchangeDAO;

	@Autowired
	CategoryDAO categoryDAO;

	@Autowired
	CategoryList catogriesList;

	@Autowired
	UserDAO userDAO ;

	@Autowired
	BidDAO bidDAO ;

	@Autowired
	NetworkDAO networkDao;

	@Autowired
	NetworkList networkList;

	@Autowired
	CompanyDAO companyDao;

	@Autowired
	EmployeeDAO employeeDao;

	@RequestMapping(value = "/product.htm")
	public ModelAndView companyProduct(HttpServletRequest request,
			HttpServletResponse response)throws AdException{

		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}

		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Company")){
			return new ModelAndView("acessDenied");
		}
		
		AdverstisingCompany company = companyDao.getInt(useracccSession);
		ArrayList<Catogries> catogries = (ArrayList<Catogries>) categoryDAO.getA(company);


		catogriesList.setCatogriesList(catogries);

		request.setAttribute("cat",catogriesList);
		return new ModelAndView("addProduct","cat",catogriesList);
	}	
	
	
	@RequestMapping(value = "/company.htm")
	public ModelAndView company(HttpServletRequest request,
			HttpServletResponse response)throws AdException{

		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Admin")){
			return new ModelAndView("acessDenied");
		}
		
		AdverstisingCompany company = new AdverstisingCompany();
		
		List<Network> network = networkDao.getAll();
		networkList.setNetwork((ArrayList<Network>) network);

		List<Employee> employee = employeeDao.getAll();
		networkList.setEmList((ArrayList<Employee>) employee);

		request.getSession().setAttribute("list2", networkList);
		return new ModelAndView("addCompany","company",company);
	}
	
	
	
	
	
	
	@RequestMapping(value = "/addCompany.htm")
	public ModelAndView addCompany(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute("company")AdverstisingCompany company ,BindingResult result)throws AdException{

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
		String name = request.getParameter("compname");
		String net = request.getParameter("net");
		String emp = request.getParameter("emp");

		ComapnyValidator comapnyValidator= new ComapnyValidator();
		comapnyValidator.validate(company, result);
		if(result.hasErrors()){
			return new ModelAndView("addCompany");
		}
		
		
		Network network = networkDao.get(net);
		Employee employee = employeeDao.get(emp);
		
		AdverstisingCompany adverstisingCompany = companyDao.getByEmployee(employee);
		System.out.println(adverstisingCompany);
		
		Exchange exc = exchangeDAO.getByEmployee(employee);
		System.out.println(exc);
		
		if((exc != null) || (adverstisingCompany != null)){
			return new ModelAndView("addCompany");
		}
		
		User user1 = userDAO.get(username);
		if(user1 != null){
			return new ModelAndView("addCompany");
		}
		
		
		userDAO.create(username, password,"Company");
		User user = userDAO.get(username);
		

		companyDao.create(name, user,network, employee,company);


		DAO.close();

		return new ModelAndView("addCompanySucess");
	}


	@RequestMapping(value = "/categories.htm")
	public ModelAndView companyCat(HttpServletRequest request,
			HttpServletResponse response)throws AdException{

		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Company")){
			return new ModelAndView("acessDenied");
		}
		
		
		return new ModelAndView("Category");
	}

	@RequestMapping(value = "/addCategories.htm")
	public ModelAndView addCategory(HttpServletRequest request,
			HttpServletResponse response)throws AdException{

		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Company")){
			return new ModelAndView("acessDenied");
		}
		
		
		String name = request.getParameter("name");

		Catogries catogries=categoryDAO.get(name);
		if(catogries!=null){
			return new ModelAndView("Category");
		}
		
		if(name.trim().equals("")){
			return new ModelAndView("Category");
		}
		
		User user =(User)request.getSession().getAttribute("session");


		AdverstisingCompany company = user.getAdverstisingCompany();


		categoryDAO.create(name,company);

		DAO.close();
		return new ModelAndView("addCategorySucess");
	}

	@RequestMapping(value = "/addProduct.htm")
	public ModelAndView addProduct(HttpServletRequest request,
			HttpServletResponse response)throws AdException{

		if(request.getSession().getAttribute("session")==null){
			System.out.println("session true");
			return new ModelAndView("acessDenied");
		}
		
		User useracccSession = (User) request.getSession().getAttribute("session");
		if(!useracccSession.getRole().equals("Company")){
			return new ModelAndView("acessDenied");
		}
		
		
		String name = request.getParameter("name");
		String pric= request.getParameter("price");
		String category = request.getParameter("product");
		System.out.println("Category"+category);
		int price = Integer.parseInt(pric);

		
		Catogries catogries2= categoryDAO.get(category);

		//		System.out.println("Category name"+catogries2.getCategoryName());


		productDAO.create(name, price, catogries2);


		DAO.close();

		return new ModelAndView("addProductSucess");
	}


//	@RequestMapping(value = "/addBid.htm")
//	public ModelAndView addBid(HttpServletRequest request,
//			HttpServletResponse response, @ModelAttribute("image") Image image, BindingResult result)throws AdException, Exception, IOException{
//
//		if(request.getSession().getAttribute("session")==null){
//			System.out.println("session true");
//			return new ModelAndView("acessDenied");
//		}
//		
//		String occupation = request.getParameter("occupation");
//		String gender= request.getParameter("gender");
//		String age = request.getParameter("age");
//		String compType = request.getParameter("compType");
//		String product = request.getParameter("product");
//		String exchange = request.getParameter("Exchange");
//		String ad1 = request.getParameter("adtype");
//		String pric = request.getParameter("price");
//		Product pro = productDAO.get(product);
//		int price =Integer.parseInt(pric);
//
//		
//		CommonsMultipartFile multiPartFile = image.getImage();
//		File destination = new File("C:\\Users\\Avi\\Documents\\workspace-sts-3.5.0.RELEASE\\Internet_Advertising\\src\\main\\webapp\\resources\\", multiPartFile.getOriginalFilename());
//		multiPartFile.transferTo(destination);
//		
//		System.out.println(destination.getAbsolutePath().substring(destination.getAbsolutePath().lastIndexOf("webapp\\")+1));
//		
//		String file = destination.getAbsolutePath();
//		
//		System.out.println(file);
//		Exchange exc = exchangeDAO.get(exchange);
//		System.out.println(exc.getExchangeName());
//
//
//
//		bidDAO.create(occupation, gender, compType, age, pro, exc, ad1,price,file);
//
//
//
//		//Exchange
//
//
//
//		return new ModelAndView("addBidSucess");
//	}
}
