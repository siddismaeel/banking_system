package com.real.bank.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.real.bank.model.Customer;
import com.real.bank.model.TX;
import com.real.bank.service.TransactionService;
import com.real.bank.service.TransactionServiceImpl;


@WebFilter("/reset-pin")
public class ResetPin implements Filter {

	private static final Logger LOGGER = Logger.getLogger(ResetPin.class);
   
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		response.setContentType("text/plain");  
	    response.setCharacterEncoding("UTF-8");
	    
		HttpSession session = ((HttpServletRequest)request).getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		request.setAttribute("customer", customer);
		
		String oldPin = request.getParameter("oldPin");
		String newPin = request.getParameter("newPin");
		String matchedPin = request.getParameter("matchedPin");
		
		if(Integer.parseInt(oldPin) == customer.getAccount().getPin()) // if the old pin matched
		{
			if(newPin.equals(matchedPin)) //if re entered pin matched
			{
				//setting the new pin
				customer.getAccount().setPin(Integer.parseInt(newPin));
				chain.doFilter(request, response);
			}
			else
			{
				response.getWriter().write("Re-enter pin did not match");
				request.setAttribute("reEnterPinNotMatch", "Re-enter pin did not match");
			}
		}
		else
		{
			LOGGER.warn("An invalid attempt to reset the pin for the account: " + customer.getAccount().getAccountNumber());
			response.getWriter().write("Old pin is incorrect");
			request.setAttribute("incorrectOldPin", "Old pin is incorrect");
		}
		
		TransactionService transactionService = new TransactionServiceImpl();
		List<TX> transactions = transactionService.getTransactions(customer.getAccount().getAccountId());
		request.setAttribute("transactions", transactions);
		
		//request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
		
	}

	
	

}
