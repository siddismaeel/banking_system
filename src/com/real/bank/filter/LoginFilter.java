package com.real.bank.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.real.bank.exceptions.invalidUserIdException;
import com.real.bank.model.Admin;
import com.real.bank.model.Customer;
import com.real.bank.service.AdminService;
import com.real.bank.service.AdminServiceImpl;
import com.real.bank.service.CustomerService;
import com.real.bank.service.CustomerServiceImpl;


@WebFilter("/login")
public class LoginFilter implements Filter {
	private static Logger LOGGER = Logger.getLogger(LoginFilter.class);
	  
	  public void doFilter(ServletRequest request, ServletResponse response,
				FilterChain chain) throws IOException, ServletException {
			CustomerService customerService = new CustomerServiceImpl();
			AdminService adminService = new AdminServiceImpl();
		  	String userId = request.getParameter("userId");
			 String pin = request.getParameter("pin");
			 String adminLogin = request.getParameter("adminLogin");
			  HttpSession session = null;
			  response.setContentType("text/html");
			  PrintWriter out = response.getWriter();
			  if(adminLogin == null)
				  adminLogin = "";
			  switch(adminLogin) {
			  
			  case "1":
				  
				  Admin admin = adminService.getAdmin(userId);
				  if(admin != null)
				  {
					  if(admin.getUserId().equals(userId) && admin.getPassword().equals(pin))
					  {
						  session = ((HttpServletRequest)request).getSession();
						  LOGGER.info("Admin logged in");
						  session.setAttribute("admin", admin);
						  request.getRequestDispatcher("admin-login").forward(request, response);
					  }
					  else
					  {
						  
						  response.getWriter().write("Invalid Credentials");
						  request.setAttribute("loginStatus", "Invalid Credentials");
						  LOGGER.warn("Admin login attempt with wrong user id or password");
					  }
				  }
				  else
				  {
					  out.print("User does not exixts");
					  //request.setAttribute("userNotFound", "User does not exixts");
					  LOGGER.warn("Admin login attempt with wrong user id or password");
				  }
				  
				  break;
			  case "0":
				  try {
					Customer customer = customerService.getCustomerInfo(userId);
					if(customer != null)
					  {
						  if(customer.getAccount().getUserId().equals(userId) && customer.getAccount().getPin() == Integer.parseInt(pin))
						  {
							  session = ((HttpServletRequest)request).getSession();
							  LOGGER.info("Customer with user id: " + customer.getAccount().getUserId() + " logged in");
							  session.setAttribute("customer", customer);
							  chain.doFilter(request, response);
						  }
						  else
						  {
							  out.print("Invalid Credentials");
							  request.setAttribute("loginStatus", "Invalid Credentials");
						  }
					  }
					  else
					  {
						  out.print("User does not exixts");
						  request.setAttribute("userNotFound", "User does not exixts");
					  }
				} catch (invalidUserIdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
				  
				  break;
				  default:
					  if(((HttpServletRequest)request).getMethod().equalsIgnoreCase("get"))
						  chain.doFilter(request, response);
					  
						  
			  
			  }
			  
			  //request.getRequestDispatcher("home").forward(request, response);
			
			  
			  
			 }
	  
	 }
