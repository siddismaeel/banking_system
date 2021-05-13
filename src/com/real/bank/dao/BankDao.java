package com.real.bank.dao;

import java.util.List;

import com.real.bank.model.Bank;

public interface BankDao {
	
	public Bank getBank(int bankId);
	public List<Bank> getAllBanks();
	public void updateBank(Bank bank);
	public void insertBank(Bank bank);

}
