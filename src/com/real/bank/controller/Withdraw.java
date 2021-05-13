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

import org.apache.log4j.Logger;

import com.real.bank.exceptions.NegativeBalanceException;
import com.real.bank.exceptions.OutOfBalanceException;
import com.real.bank.model.Customer;
import com.real.bank.model.TX;
import com.real.bank.service.EmailService;
import com.real.bank.service.EmailServiceImpl;
import com.real.bank.service.TransactionService;
import com.real.bank.service.TransactionServiceImpl;
import com.real.bank.util.IDGenerator;


@WebServlet("/withdraw-amount")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TransactionService transactionService = new TransactionServiceImpl();
	private static final Logger LOGGER = Logger.getLogger(Withdraw.class);
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
		String amount = request.getParameter("amount");
		HttpSession session = request.getSession();
		response.setContentType("text/plain");  
	    response.setCharacterEncoding("UTF-8"); 
		Customer customer = (Customer)session.getAttribute("customer");
		
		
		TX tx = new TX();
		tx.setTransactionId(IDGenerator.generateTransactionId());
		tx.setAmount(Double.parseDouble(amount));
		tx.setAccount(customer.getAccount());
		tx.setBalance(customer.getAccount().getBalance());
		tx.setTimeStamp(new Date(System.currentTimeMillis()));
		tx.setDescription("Amount withdrawn");
		request.setAttribute("customer", customer);
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				EmailService emailService = new EmailServiceImpl();
				emailService.sendWithdrawTransactionStatus(customer, tx);
				
			}
			  
		  };
		
		if(customer.getAccount().isActive()) // if account is activated
		{
			try { 
				double withdraw = transactionService.withdraw(tx); // Then withdraw the amount
				//new Thread(r).start();
				response.getWriter().write("Amoun: " + amount + " has been withdrawn");
				LOGGER.info("Amount: " + amount + " has been withdrawn from the account: " + customer.getAccount().getAccountNumber());
			} catch (NegativeBalanceException e) {
				response.getWriter().write("Amount should be greater than zero");
				//if user enters amount in negative
				request.setAttribute("NegativeBalanceException", "Balannce should be greater than zero");
			} catch (OutOfBalanceException e) {
				// if account does not have sufficient balance
				response.getWriter().write("Insufficient balance");
				request.setAttribute("OutOfBalanceException", "Insufficient balance");
			}
		}
		else
		{
			request.setAttribute("withdraw", "Acount is not active!");
		}
		
		List<TX> transactions = transactionService.getTransactions(customer.getAccount().getAccountId());
		request.setAttribute("transactions", transactions);
		
		//request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

}
