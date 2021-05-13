package com.real.bank.dao;

import java.util.List;

import com.real.bank.model.Account;

public interface AccountDAO {

	public int addAccount(Account account);
	public Account getAccountInfo(int accountId);
	public Account getAccountInfoBycustomerId(int customerId);
	public int updateBalance(Account account);
	public Account getAccountInfo(long accountNumber);
	public Account getAccountInfo(String userId);
	public int resetUserId(Account account, String newUserId);
	public int resetPin(Account account);
	public List<Account> getAllAccounts();
	public int activateAccount(long accountNumber);
	public int deactivateAccount(long accountNumber);
	public int deleteAccount(long accountNumber);
}
