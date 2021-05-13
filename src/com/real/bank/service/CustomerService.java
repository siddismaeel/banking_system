package com.real.bank.service;

import com.real.bank.exceptions.invalidUserIdException;
import com.real.bank.model.Customer;

public interface CustomerService {
	
	public int addCustomer(Customer customer);
	public Customer getCustomerInfo(int id);
	public Customer getCustomerInfo(String userId)throws invalidUserIdException;
	public int resetUserId(Customer customer, String newUserId);
	public Customer getCustomerInfo(long accountNumber);
	

}
