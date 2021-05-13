package com.real.bank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.real.bank.model.Account;
import com.real.bank.model.Admin;
import com.real.bank.service.AccountService;
import com.real.bank.service.AccountServiceImpl;

@WebServlet("/admin-login")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AccountService accountService = new AccountServiceImpl();

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Account> accounts = accountService.getAllAccounts();
		request.setAttribute("accounts", accounts);
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		request.setAttribute("admin", admin);
		
		
		//request.getRequestDispatcher("/WEB-INF/views/admin-dashboard.jsp").forward(request, response);
		request.getRequestDispatcher("/WEB-INF/admin-dashboard.html").forward(request, response);
		
	}

}
