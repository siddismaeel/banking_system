package com.real.bank.service;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.real.bank.dao.AccountDAO;
import com.real.bank.dao.AccountDAOImpl;
import com.real.bank.dao.CustomerDAO;
import com.real.bank.dao.CustomerDAOImpl;
import com.real.bank.exceptions.invalidUserIdException;
import com.real.bank.model.Account;
import com.real.bank.model.Customer;
import com.real.bank.util.PDFGenerator;
import com.real.bank.util.Validator;

public class CustomerServiceImpl implements CustomerService {

	EmailService emailService = new EmailServiceImpl();
	private CustomerDAO customerDao = new CustomerDAOImpl();
	private AccountDAO accountDao = new AccountDAOImpl();
	private AccountService accountService = new AccountServiceImpl();
	private static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class);
	@Override 
	public int addCustomer(Customer customer) {
		LOGGER.info("begins addCustomer method");
		int rowsaffect = customerDao.addCustomer(customer);

		Runnable r = new Runnable() 
		{

			@Override
			public void run() {
				
				try { 
					PDFGenerator.generateCustomerDetailsPDF(customer);
					emailService.sendWelcomeEmail(customer);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					LOGGER.info("exception in addCustomer method " + e1);
				}
			}
			
		};
		if (rowsaffect > 0)
			new Thread(r).start();
			

		LOGGER.info("ends addCustomer method");
		return rowsaffect;
	}

	@Override
	public Customer getCustomerInfo(int id) {
		LOGGER.info("begins getCustomerInfo method by id");
		Customer customer = customerDao.getCustomerInfo(id);
		if(customer != null)
		{
			Account account = accountService.getAccountInfoByCustomerId(id);
			customer.setAccount(account);
		}
		LOGGER.info("ends getCustomerInfo method by id");
		return customer;
	}

	@Override
	public Customer getCustomerInfo(String userId) throws invalidUserIdException {
		LOGGER.info("begins getCustomerInfo method by user id");
		if(!Validator.isValidUserId(userId))
		{
			throw new invalidUserIdException();
		}
		Customer customer = customerDao.getCustomerInfo(userId);
		if(customer != null)
		{
		
			customer.setAccount(accountDao.getAccountInfo(userId));
		}
		LOGGER.info("ends getCustomerInfo method by user id");
		return customer;
	}

	@Override
	public int resetUserId(Customer customer, String newUserId) {
		
		LOGGER.info("begins resetUserId method");
		int rowsAffected = accountService.resetUserId(customer.getAccount(), newUserId);
		if(rowsAffected > 0)
		{
			customer.getAccount().setUserId(newUserId);
			try {
				PDFGenerator.generateCustomerDetailsPDF(customer);
				emailService.sendWelcomeEmail(customer);
			} catch (IOException e) {
				
				LOGGER.info("exceptin in resetUserId method " + e);
			}
			finally
			{
				return rowsAffected;
			}
			
			
		}
		
		LOGGER.info("ends resetUserId method");
		return rowsAffected;
	}

	@Override
	public Customer getCustomerInfo(long accountNumber)
	{
		LOGGER.info("begins getCustomerInfo method by account number"); 
		Customer customer = customerDao.getCustomerInfo(accountNumber);
		LOGGER.info("ends getCustomerInfo method by account number"); 
		return customer;
	}

	

}
