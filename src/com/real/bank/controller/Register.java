package com.real.bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.real.bank.model.Customer;
import com.real.bank.service.CustomerService;
import com.real.bank.service.CustomerServiceImpl;
import com.real.bank.service.EmailService;
import com.real.bank.service.EmailServiceImpl;


@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private CustomerService customerService = new CustomerServiceImpl();
       private Logger LOGGER = Logger.getLogger(Register.class);
       
       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   LOGGER.info("In Regiser servlet");
		response.setContentType("text/plain");  
	    response.setCharacterEncoding("UTF-8"); 
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		EmailService emailService = new EmailServiceImpl();
		
		
		Integer step = (Integer) session.getAttribute("step");
		
		if(step == null)
			step = 0;
		
		switch(step)
		{
		case 0:
			System.out.println("in case 0");
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					int otp = emailService.sendOTP(customer);
					session.setAttribute("otp", otp);
				}
				
			}).start();
			 
			session.setAttribute("step", step+1);
			request.setAttribute("url", "./register");
			response.getWriter().write("Otp sent");
			//request.getRequestDispatcher("/WEB-INF/views/otp.jsp").forward(request, response);
			break;
		case 1:
			System.out.println("in case 0");
			if(Integer.parseInt(request.getParameter("otp")) == (Integer)session.getAttribute("otp"))
			{
				
				int addCustomer = customerService.addCustomer(customer);
				
				if(addCustomer > 0)
				{
					LOGGER.info("A customer registered as user id: " + customer.getAccount().getUserId());
					response.getWriter().write("Customer added successfully!");
					request.setAttribute("customerAdded", "Customer added successfully!");
					session.invalidate();
					
					
				}
				else
				{
					request.setAttribute("customerAdded", "Customer not added.");
				}
			}
			else
			{
				response.getWriter().write("OTP did not match");
			}
			break;
			default:
		}
		
		
		//request.getRequestDispatcher("home").forward(request, response);
		
		
		
		
		
		
		
	}

}
