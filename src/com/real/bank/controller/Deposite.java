package com.real.bank.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.real.bank.exceptions.NegativeBalanceException;
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
import com.real.bank.util.IDGenerator;


@WebServlet(name = "Deposit", urlPatterns = { "/deposit-amount" })
public class Deposite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(Deposite.class);
	CustomerService customerService = new CustomerServiceImpl();
	AccountService accountService = new AccountServiceImpl();
	TransactionService transactionService = new TransactionServiceImpl();
	   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("A request has come in deposit");
		String amount = request.getParameter("amount");
		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		response.setContentType("text/plain");  
	    response.setCharacterEncoding("UTF-8"); 
	    
		TX tx = new TX();
		tx.setTransactionId(IDGenerator.generateTransactionId());
		tx.setAccount(customer.getAccount());
		tx.setAmount(Double.parseDouble(amount));
		tx.setTimeStamp(new Date(System.currentTimeMillis()));
		tx.setDescription("Deposit Amount");
		
		//response.setContentType("text/xml");
		Writer out = response.getWriter();
		String responseStr = "";
		
		Runnable r = new Runnable() {
 
			@Override
			public void run() {
				EmailService emailService = new EmailServiceImpl();
				emailService.sendDepositeTransactionStatus(customer, tx);
				
			}
			  
		  };
		
		double depositedAmount;
		try {
			depositedAmount = transactionService.deposite(tx);
			//new Thread(r).start();
			LOGGER.info("Amount " + amount + " has been deposited in account " + customer.getAccount().getAccountNumber());
			response.getWriter().write("Amoun: " + amount + " has been deposited");
		} catch (NegativeBalanceException e) {
			//if user enters amount in negative
			response.getWriter().write("Amount should be greater than zero");
			request.setAttribute("NegativeBalanceException", "Amount should be greater than zero");
		}
		
		List<TX> transactions = transactionService.getTransactions(customer.getAccount().getAccountId());
		request.setAttribute("transactions", transactions);
		
		
		//out.write(responseStr);
		request.setAttribute("customer", customer);
		//request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
		
		
	}

}
