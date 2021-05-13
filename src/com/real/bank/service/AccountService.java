package com.real.bank.service;

import java.util.List;

import com.real.bank.model.Account;

public interface AccountService {

	public List<Account> getAllAccounts();
	public int addAccount(Account account);
	public Account getAccountInfo(int accountId);
	public int updateBalance(Account account);
	public Account getAccountInfo(long accountNumber);
	public int resetUserId(Account account, String newUserId);
	public int resetPin(Account account);
	public int activateAccount(long parseLong);
	public int deactivateAccount(long parseLong);
	public int deleteAccount(long parseLong);
	public Account getAccountInfoByCustomerId(int customerId);
}
