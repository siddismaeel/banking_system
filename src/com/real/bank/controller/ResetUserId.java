package com.real.bank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.real.bank.model.Customer;
import com.real.bank.model.TX;
import com.real.bank.service.AccountService;
import com.real.bank.service.AccountServiceImpl;
import com.real.bank.service.EmailService;
import com.real.bank.service.EmailServiceImpl;
import com.real.bank.service.TransactionService;
import com.real.bank.service.TransactionServiceImpl;
import com.real.bank.util.PDFGenerator;


@WebServlet("/reset-user-id")
public class ResetUserId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ResetUserId.class);
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountService accountService = new AccountServiceImpl();
		EmailService emailService = new EmailServiceImpl();
		response.setContentType("text/plain");  
	    response.setCharacterEncoding("UTF-8");
	     
		String newUserId = request.getParameter("userId");
		String pin = request.getParameter("pin");
		TransactionService transactionService = new TransactionServiceImpl();
		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		int updated = 0;
		Runnable r = new Runnable() {

			@Override
			public void run() {
				EmailService emailService = new EmailServiceImpl();
				try {
					PDFGenerator.generateCustomerDetailsPDF(customer);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				emailService.senduserIdResetEmail(customer);
				
				
			}
			  
		  };
		
		if(Integer.parseInt(pin) == customer.getAccount().getPin()) //if pin matches
		{
			//updating the user id
			updated = accountService.resetUserId(customer.getAccount(), newUserId);
			if(updated > 0)
			{
				new Thread(r).start();
				response.getWriter().write("User ID has been reset successfully");
			}
			else
			{
				response.getWriter().write("User ID already exists!");
			}
			
		}
		else
		{
			response.getWriter().write("Password did not match!");
			request.setAttribute("pinStatus", "Password did not match!");
		}
		
		
		if(updated > 0) // if user id updated
		{
			customer.getAccount().setUserId(newUserId);
			emailService.senduserIdResetEmail(customer); //sending mail to associated account holder
			LOGGER.info("User id updated for the account number: " + customer.getAccount().getAccountNumber());
			request.setAttribute("updated", "User Id updated successfully!");
		}
		else
		{
			request.setAttribute("updated", "User Id not updated");
		}
		
		
		List<TX> transactions = transactionService.getTransactions(customer.getAccount().getAccountId());
		request.setAttribute("transactions", transactions);
		request.setAttribute("customer", customer);
		//request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
		
	}

}
