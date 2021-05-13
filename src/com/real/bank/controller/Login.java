package com.real.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.real.bank.model.Customer;
import com.real.bank.model.TX;
import com.real.bank.service.TransactionService;
import com.real.bank.service.TransactionServiceImpl;


@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		//response.setContentType("text/plain");
		//response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		TransactionService transactionService = new TransactionServiceImpl();
		 
		Customer customer = (Customer)session.getAttribute("customer");
		List<TX> transactions = transactionService.getTransactions(customer.getAccount().getAccountId());
		System.out.println(transactions);
		request.setAttribute("customer", customer);
		request.setAttribute("transactions", transactions);
		System.out.println("Login successful");
		out.write("done");
		
		//request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
		request.getRequestDispatcher("/WEB-INF/dashboard.html").forward(request, response);
		//response.sendRedirect(request.getServletContext().getContextPath() + "/WEB-INF/dashboard.html");
		
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/login.html").forward(request, response);
		
	}

	
	
	
	

}
