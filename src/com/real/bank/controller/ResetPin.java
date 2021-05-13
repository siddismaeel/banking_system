package com.real.bank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.real.bank.model.Customer;
import com.real.bank.model.TX;
import com.real.bank.service.AccountService;
import com.real.bank.service.AccountServiceImpl;
import com.real.bank.service.EmailService;
import com.real.bank.service.EmailServiceImpl;
import com.real.bank.service.TransactionService;
import com.real.bank.service.TransactionServiceImpl;

@WebServlet("/reset-pin")
public class ResetPin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ResetPin.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/plain");  
	    response.setCharacterEncoding("UTF-8");
	    
		AccountService accountService = new AccountServiceImpl();
		TransactionService transactionService = new TransactionServiceImpl();
		EmailService emailService = new EmailServiceImpl();
		
		Customer customer = (Customer)request.getAttribute("customer");
		
		Runnable r = new Runnable() {

			@Override
			public void run() {
				EmailService emailService = new EmailServiceImpl();
				emailService.senduserIdResetEmail(customer);
				
			}
			  
		  };
		
		//updating the pin
		int updated = accountService.resetPin(customer.getAccount());
		
		if(updated > 0) // pin updated
		{
			LOGGER.warn("The pin was reset for the user: " + customer.getAccount().getUserId()); //writing log info
			
			 //sending the mail to associated account
			new Thread(r).start();
			response.getWriter().write("Pin updated successfully");
			request.setAttribute("pinUpdated", "Pin updated successfully");
		}
		request.setAttribute("customer", customer);
		List<TX> transactions = transactionService.getTransactions(customer.getAccount().getAccountId()); // getting all transaction details for associated account
		request.setAttribute("transactions", transactions);
		
		//request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
		
		
		
	}

}
