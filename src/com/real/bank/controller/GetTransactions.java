package com.real.bank.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.real.bank.exceptions.invalidUserIdException;
import com.real.bank.model.Customer;
import com.real.bank.model.TX;
import com.real.bank.service.AccountService;
import com.real.bank.service.AccountServiceImpl;
import com.real.bank.service.CustomerService;
import com.real.bank.service.CustomerServiceImpl;
import com.real.bank.service.EmailService;
import com.real.bank.service.EmailServiceImpl;
import com.real.bank.service.TransactionService;
import com.real.bank.service.TransactionServiceImpl;

@WebServlet("/get-transactions")
public class GetTransactions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerService customerService = new CustomerServiceImpl();
	AccountService accountService = new AccountServiceImpl();
	EmailService emailService = new EmailServiceImpl();
	TransactionService transactionService = new TransactionServiceImpl();
	
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fromDate = request.getParameter("from-date");
		String toDate = request.getParameter("to-date");
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");
		Customer customer = null;
		
		if(userId == null)
		{
			response.sendRedirect("errorPage");
		}
		
		try {
			
			customer = customerService.getCustomerInfo(userId);
		} catch (invalidUserIdException e) {
			
			e.printStackTrace();
		}
		List<TX> transactions = transactionService.getTransactions(customer, new Date(fromDate), new Date(toDate));
		
		session.setAttribute("transactions", transactions);
		
	}

}
