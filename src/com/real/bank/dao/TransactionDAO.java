package com.real.bank.dao;

import java.util.Date;
import java.util.List;

import com.real.bank.model.Customer;
import com.real.bank.model.TX;

public interface TransactionDAO {
	
	public TX getTransactionDetails(long txId);
	public List<TX> getTransactions(int accountId);
	public List<TX> getTransactions(int accountId, Date onDate);
	public List<TX> getTransactions(int accountId, Date fromDate, Date toDate);
	public List<TX> getTransactionsOfLast6Months(int accountId);
	public double deposite(TX tx);
	public double withdraw(TX tx);
	public TX getTransactionDetails(Customer customer, long transactionId);
	

}
