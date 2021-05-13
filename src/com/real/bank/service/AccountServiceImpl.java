package com.real.bank.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.real.bank.dao.AccountDAO;
import com.real.bank.dao.AccountDAOImpl;
import com.real.bank.model.Account;
import com.real.bank.util.PDFGenerator;


public class AccountServiceImpl implements AccountService {
	private static final Logger LOGGER = Logger.getLogger(AccountServiceImpl.class);
	AccountDAO accountDao = new AccountDAOImpl();
	
	@Override
	public int addAccount(Account account) {
		LOGGER.info("begins addAccount method");
		return accountDao.addAccount(account);
	}

	@Override
	public Account getAccountInfo(int accountId) {
		LOGGER.info("begins getAccountInfo method by accountID");
		return null;
	}

	@Override
	public int updateBalance(Account account) {
		LOGGER.info("begins updateBalance method");
		int affectedRows = accountDao.updateBalance(account);
		LOGGER.info("ends getAccountInfo method");
		return affectedRows;
	}

	@Override
	public Account getAccountInfo(long accountNumber) {
		LOGGER.info("begins getAccountInfo method by account number");
		Account account = accountDao.getAccountInfo(accountNumber);
		LOGGER.info("ends getAccountInfo method by account number");
		return account;
	}

	@Override
	public int resetUserId(Account account, String newUserId) {
		LOGGER.info("begins resetUserId method");
		int updatedRows = accountDao.resetUserId(account, newUserId);
		
		LOGGER.info("ends resetUserId method");
		return updatedRows;
	}

	@Override
	public int resetPin(Account account) {
		LOGGER.info("begins resetPin method");
		int rows  = accountDao.resetPin(account);
		LOGGER.info("ends resetPin method");
		return rows;
	}

	@Override
	public List<Account> getAllAccounts() {
		LOGGER.info("begins getAllAccounts method");
		List<Account> accounts = accountDao.getAllAccounts();
		LOGGER.info("ends getAllAccounts method");
		return accounts;
	}

	@Override
	public int activateAccount(long accountNumber) {
		LOGGER.info("begins activateAccount method");
		LOGGER.info("ends activateAccount method");
		return accountDao.activateAccount(accountNumber);
		
	}

	@Override
	public int deactivateAccount(long accountNumber) {
		LOGGER.info("begins deactivateAccount method");
		LOGGER.info("begins deactivateAccount method");
		return accountDao.deactivateAccount(accountNumber);
		
	}

	@Override
	public int deleteAccount(long accountNumber) {
		LOGGER.info("begins deleteAccount method");
		LOGGER.info("ends deleteAccount method");
		return accountDao.deleteAccount(accountNumber);
		
	}

	@Override
	public Account getAccountInfoByCustomerId(int customerId) {
		LOGGER.info("begins getAccountInfoByCustomerId method");
		Account account = accountDao.getAccountInfoBycustomerId(customerId);
		LOGGER.info("ends getAccountInfoByCustomerId method");
		return account;
		
	}

	

}
