package com.real.bank.service;

import com.real.bank.model.Customer;
import com.real.bank.model.TX;

public interface EmailService {

	public int sendOTP(Customer customer);
	public void sendWelcomeEmail(Customer customer);
	public void senduserIdResetEmail(Customer customer);
	public void sendDepositeTransactionStatus(Customer customer, TX tx);
	public void sendWithdrawTransactionStatus(Customer customer, TX tx);
	public void sendTransactionStatus(Customer customer);
	public void sendTransactions(Customer customer);
	
	
}
