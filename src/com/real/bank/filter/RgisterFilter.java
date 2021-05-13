package com.real.bank.filter;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.real.bank.controller.Register;
import com.real.bank.exceptions.InvalidEmailException;
import com.real.bank.exceptions.InvalidMobileNumberException;
import com.real.bank.model.Account;
import com.real.bank.model.Customer;
import com.real.bank.util.IDGenerator;

/**
 * Servlet Filter implementation class RgisterFilter
 */
@WebFilter("/register")
public class RgisterFilter implements Filter {
	private Logger LOGGER = Logger.getLogger(Register.class);
	Customer customer = new Customer();
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		LOGGER.info("begins RgisterFilter filter");
		response.setContentType("text/plain");  
	    response.setCharacterEncoding("UTF-8"); 
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		
		System.out.println(((HttpServletRequest)request).getMethod());
		if(((HttpServletRequest)request).getMethod().equalsIgnoreCase("GET"))
		{
			System.out.println("in chain block");
			request.getRequestDispatcher("/login").forward(request, response);
		}
			
		
		
		 
		boolean isValid = false;
		Integer step = (Integer)session.getAttribute("step");
		
		if(step != null && step > 0)
			chain.doFilter(request, response);
		else
			isValid = validate(request);
		
		System.out.println("Request is valid: " + isValid);
		
		if(isValid)
		{
			LOGGER.info("request chained RgisterFilter filter");
			chain.doFilter(request, response);
		}
		else
		{
			response.getWriter().write("You enterd incorrrect data");
			//request.getRequestDispatcher("/home").forward(request, response);
		}
		
		
		LOGGER.info("ends RgisterFilter filter");
	}

	private boolean validate(ServletRequest request) {
		
		boolean valid = false;
		
		String accountType = request.getParameter("accountType");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String pinCode = request.getParameter("pinCode");
		String email = request.getParameter("email");
		String beginBalance = request.getParameter("beginBalance");
		String contact = request.getParameter("contact");
		request.setAttribute("fName", fName);
		request.setAttribute("lName", lName);
		request.setAttribute("street", street);
		request.setAttribute("city", city);
		request.setAttribute("state", state);
		request.setAttribute("pinCode", pinCode);
		request.setAttribute("email", email);
		request.setAttribute("beginBalance", beginBalance);
		request.setAttribute("contact", contact);
		System.out.println("First name " + fName);
		if(!accountType.equalsIgnoreCase("..Select Account Type.."))
		{
			valid = true;
			
		}
		else
		{
			
			request.setAttribute("accountTypeRequired", "Please select account type");
			return false;
		}
		if(!fName.equals(""))
		{
			valid = true;
			
		}
		else
		{
			
			request.setAttribute("fNameRequired", "First name is required!");
			return false;
		}
		
		if(!lName.equals(""))
		{
			valid = true;
			
		}
		else
		{
			
			request.setAttribute("lNameRequired", "Last name is required!");
			return false;
		}
		
		if(!street.equals(""))
		{
			valid = true;
			
		}
		else
		{
			
			request.setAttribute("streetRequired", "Street is required!");
			return false;
		}
		
		if(!city.equals(""))
		{
			valid = true;
			
		}
		else
		{
			
			request.setAttribute("cityRequired", "City is required!");
			return false;
		}
		
		if(!state.equals(""))
		{
			valid = true;
			
		}
		else
		{
			
			request.setAttribute("stateRequired", "State is required!");
			return false;
		}
		
		if(!pinCode.equals(""))
		{
			valid = true;
			
		}
		else
		{
			
			request.setAttribute("pinCodeRequired", "Pin code is required!");
			return false;
		}
		
		if(!email.equals(""))
		{
			valid = true;
			
		}
		else
		{
			
			request.setAttribute("emailRequired", "Email is required!");
			return false;
		}
		
		if(!beginBalance.equals(""))
		{
			valid = true;
			if(accountType.equalsIgnoreCase("saving") && Double.parseDouble(beginBalance) < 700)
			{
				
				request.setAttribute("lessBalance", "Begin balance should be more than 699 for saving account");
				return false;
			}
			else if(accountType.equalsIgnoreCase("current") && Double.parseDouble(beginBalance) < 700)
			{
				
				request.setAttribute("lessBalance", "Begin balance should be more than 4999 for saving account");
				return false;
			}
			
		}
		else
		{
			
			request.setAttribute("beginBalanceRequired", "Begin balance is required!");
			return false;
		}
		
		if(!contact.equals(""))
		{
			valid = true;
			
		}
		else
		{
			
			request.setAttribute("contactRequired", "Contact is required!");
			return false;
		}
		if(valid)
		{
			customer.setCutomerId(IDGenerator.getCustomerId());
			customer.setFirstName(fName);
			customer.setLastName(lName);
			customer.setStreet(street);
			customer.setCity(city);
			customer.setState(state);
			customer.setPin(Integer.parseInt(pinCode));
			
			try {
				customer.setContact(contact);
				customer.setEmail(email);
			} catch (InvalidMobileNumberException e) {
				request.setAttribute("invalidMobileNumber", "Mobile number is not valid");
				return false;
			} catch (InvalidEmailException e) {
				request.setAttribute("invalidEmail", "Emai id is not valid");
				return false;
			}
			
			Account account = new Account();
			account.setAccountId(IDGenerator.getAccountId());
			account.setAccountNumber(IDGenerator.getAccountNumber());
			account.setAccountType(accountType);
			account.setActive(false);
			account.setBeginBalance(Double.parseDouble(beginBalance));
			account.setBalance(account.getBeginBalance());
			account.setPin(IDGenerator.generatePin());
			account.setTimeStamp(Calendar.getInstance().getTime());
			 
			if(accountType.equalsIgnoreCase("saving"))
			{
				account.setDescription("Saving Account");
				account.setTransactionAmountLimit(10000);
				account.setTransactionLimit(10);
			}
			else
			{
				account.setDescription("Current Account");
				account.setTransactionAmountLimit(5000);
				account.setTransactionLimit(25);
			}
			
			account.setUserId(IDGenerator.generateUserId());
			
			customer.setAccount(account);
			
			HttpSession session = ((HttpServletRequest)request).getSession();
			session.setAttribute("customer", customer);
			
		}
		
		
		return valid;
	}

	
}
