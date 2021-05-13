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

import com.real.bank.model.Account;
import com.real.bank.model.Admin;
import com.real.bank.service.AccountService;
import com.real.bank.service.AccountServiceImpl;


@WebServlet("/delete-account")
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       AccountService accountService = new AccountServiceImpl();
       private static Logger LOGGER = Logger.getLogger(DeleteAccount.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String accountNumber = request.getParameter("accountNumber");
		
		//Deleting the account
		int deletedAccount = accountService.deleteAccount(Long.parseLong(accountNumber));
		
		if(deletedAccount > 0) // if account deleted
		{
			LOGGER.warn("Account number " + accountNumber + " Deleted");
		}
		else
		{
			LOGGER.warn("Account number " + accountNumber + " was not Deleted");
		}
		//Getting all accounts in the system
		List<Account> accounts = accountService.getAllAccounts();
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		
		request.setAttribute("admin", admin);
		request.setAttribute("accounts", accounts);
		
		//jumping to the admin dashboard page
		request.getRequestDispatcher("/WEB-INF/views/admin-dashboard.jsp").forward(request, response);
		
	}

}
