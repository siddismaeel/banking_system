package com.real.bank.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.real.bank.model.Customer;


@WebFilter("/deposit-amount")
public class Deposit implements Filter {

    public Deposit() {
        // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		
		if(session == null)
		{
			response.getWriter().write("Invalid Credentials");
		}
		Customer customer = (Customer)session.getAttribute("customer");
		String amount = request.getParameter("amount");
		System.out.println("amount " + amount);
		
		try
		{
			Double.parseDouble(amount);
			chain.doFilter(request, response);
		}
		catch(NumberFormatException e)
		{
			response.getWriter().write("Amount is not valid");
			System.out.println("Could not depsite amount");
		}
		
		
		System.out.println("Filter called");
		
	}

	
	

}
