package com.real.bank.dao;

import com.real.bank.model.Customer;

public interface CustomerDAO {

	public int addCustomer(Customer customer);
	public Customer getCustomerInfo(int id);
	public Customer getCustomerInfo(long accountNumber);
	public Customer getCustomerInfo(String userId);
	
}
