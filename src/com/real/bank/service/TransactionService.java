package com.real.bank.service;

import java.util.Date;
import java.util.List;

import com.real.bank.exceptions.NegativeBalanceException;
import com.real.bank.exceptions.OutOfBalanceException;
import com.real.bank.model.Customer;
import com.real.bank.model.TX;

public interface TransactionService {
	
	
	public TX getTransactionDetails(long txId);
	public List<TX> getTransactions(int accountId);
	public List<TX> getTransactions(int accountId, Date onDate);
	public List<TX> getTransactions(Customer customer, Date fromDate, Date toDate);
	public int deposite(TX tx) throws NegativeBalanceException;
	public  int withdraw(TX tx) throws NegativeBalanceException, OutOfBalanceException;
	public TX getTransactionDetails(Customer customer, long transactionId);

}


