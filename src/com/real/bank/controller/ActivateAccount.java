package com.real.bank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.real.bank.model.Account;
import com.real.bank.service.AccountService;
import com.real.bank.service.AccountServiceImpl;


@WebServlet("/activate-account")
public class ActivateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(ActivateAccount.class); 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountService accountService = new AccountServiceImpl();
		String accountNumber = request.getParameter("accountNumber");
		
		int activatedAccount = accountService.activateAccount(Long.parseLong(accountNumber));
		
		if(activatedAccount > 0)
			LOGGER.info("Account number " + accountNumber + " Activated");
		else
			LOGGER.warn("Account number " + accountNumber + " was not activated!");
		
		List<Account> accounts = accountService.getAllAccounts();
		
		
		
		request.setAttribute("accounts", accounts);
		request.getRequestDispatcher("/WEB-INF/views/admin-dashboard.jsp").forward(request, response);
		
		
		
	}

}
