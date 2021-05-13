package com.real.bank.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.real.bank.dao.BankDao;
import com.real.bank.dao.BankDaoImpl;
import com.real.bank.model.Bank;

public class BankServiceImpl implements BankService {
	private static final Logger LOGGER = Logger.getLogger(BankServiceImpl.class);
	BankDao bankDao = new BankDaoImpl();
	@Override
	public Bank getBank(int bankId) {
		LOGGER.info("begins getBank method");
		LOGGER.info("ends getBank method");
		return bankDao.getBank(bankId);
	}

	@Override
	public List<Bank> getAllBanks() {
		LOGGER.info("begins getAllBanks method");
		LOGGER.info("begins getAllBanks method");
		return bankDao.getAllBanks();
		
	}

	@Override
	public void updateBank(Bank bank) {
		LOGGER.info("begins updateBank method");
		LOGGER.info("begins updateBank method");
		bankDao.updateBank(bank);

	}

	@Override
	public void insertBank(Bank bank) {
		LOGGER.info("begins insertBank method");
		LOGGER.info("ends insertBank method");

		bankDao.insertBank(bank);

	}

}
