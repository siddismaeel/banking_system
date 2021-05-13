package com.real.bank.service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.real.bank.dao.CustomerDAO;
import com.real.bank.dao.CustomerDAOImpl;
import com.real.bank.dao.TransactionDAO;
import com.real.bank.dao.TransactionDAOImpl;
import com.real.bank.exceptions.NegativeBalanceException;
import com.real.bank.exceptions.OutOfBalanceException;
import com.real.bank.model.Customer;
import com.real.bank.model.TX;
import com.real.bank.util.DBConnectionProvider;
import com.real.bank.util.PDFGenerator;

public class TransactionServiceImpl implements TransactionService {
	private static final Logger LOGGER = Logger.getLogger(TransactionServiceImpl.class);
	TransactionDAO transactionDao = new TransactionDAOImpl();
	EmailService emailService = new EmailServiceImpl();
	CustomerDAO customerDao = new CustomerDAOImpl();
	

	@Override
	public TX getTransactionDetails(long txId) {
		LOGGER.info("begins getTransactionDetails method");
		TX transactionDetails = transactionDao.getTransactionDetails(txId);
		LOGGER.info("ends getTransactionDetails method");
		return transactionDetails;
	}
	
	@Override
	public List<TX> getTransactions(int accountId) {
		LOGGER.info("begins getTransactions method by account id");
		List<TX> transactions = transactionDao.getTransactions(accountId);
		LOGGER.info("ends getTransactions method by account id");
		return transactions;
	}

	@Override
	public List<TX> getTransactions(int accountId, Date onDate) {
		LOGGER.info("begins getTransactions method by account id, onDate");
		List<TX> transactions = new ArrayList<>();
		
		LOGGER.info("ends getTransactions method by account id, onDate");
		return transactions;
	}

	@Override
	public List<TX> getTransactions(Customer customer, Date fromDate, Date toDate) {
		LOGGER.info("begins getTransactions method by customer, fromDate, toDate");
		List<TX> transactions = transactionDao.getTransactions(customer.getAccount().getAccountId(), fromDate, toDate);
		if(transactions != null && transactions.size() > 0)
		{
			try {
				PDFGenerator.generateTransationDetails(customer, (TX[])transactions.toArray());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOGGER.info("exception in getTransactions method by customer, fromDate, toDate " + e);
			}
		}
		
		LOGGER.info("ends getTransactions method by customer, fromDate, toDate");
		return transactions;
	}

	@Override
	public int deposite(TX tx) throws NegativeBalanceException {
		
		LOGGER.info("begins deposite method");
		int deposited = 0;
		if(tx.getAmount() <= 0)
		{
			throw new NegativeBalanceException();
		}
		
		double depositedAmount = transactionDao.deposite(tx);
		if(depositedAmount >= 0)
		{
			Customer customer = customerDao.getCustomerInfo(tx.getAccount().getAccountNumber());
			
			new Thread(new Runnable() {

				@Override
				public void run() {
					emailService.sendDepositeTransactionStatus(customer, tx);
					
				}
				
			}).start();
			//emailService.sendDepositeTransactionStatus(customer,tx);
		}
		LOGGER.info("ends deposite method");
		return deposited;
	}

	@Override
	public synchronized int withdraw(TX tx) throws NegativeBalanceException, OutOfBalanceException {
		LOGGER.info("begins withdraw method");
		int withdrawn = 0;
		if(tx.getAmount() <= 0)
		{
			throw new NegativeBalanceException();
		}
		
		if(tx.getAmount() >  tx.getAccount().getBalance())
		{
			throw new OutOfBalanceException(tx.getAmount(), tx.getAccount().getBalance());
		}
		double withdrawnAmount = transactionDao.withdraw(tx);
		
		if (withdrawnAmount >= 0) {
			Customer customer = customerDao.getCustomerInfo(tx.getAccount().getAccountNumber());
			
			new Thread(new Runnable() {

				@Override
				public void run() {
					
					emailService.sendWithdrawTransactionStatus(customer, tx);
					
				}
				
			}).start();
		}
		LOGGER.info("ends withdraw method");
		return withdrawn;
	}

	@Override
	public TX getTransactionDetails(Customer customer, long transactionId) {
		
		LOGGER.info("begins getTransactionDetails method customer, transactionId");
		TX tx =  transactionDao.getTransactionDetails(customer, transactionId);
		if(tx != null)
		{
			try {
				PDFGenerator.generateTransationDetails(customer, tx);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOGGER.info("exception in getTransactionDetails method customer, transactionId " + e);
			}
		}
		
		LOGGER.info("ends getTransactionDetails method customer, transactionId");
		return tx;
	}

}
