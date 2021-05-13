package com.real.bank.service;

import org.apache.log4j.Logger;

import com.real.bank.dao.AdminDao;
import com.real.bank.dao.AdminDaoImpl;
import com.real.bank.model.Admin;
import com.real.bank.model.Bank;

public class AdminServiceImpl implements AdminService {
	private static final Logger LOGGER = Logger.getLogger(AdminServiceImpl.class);
	AdminDao adminDao = new AdminDaoImpl();
	BankService bankService = new BankServiceImpl();
	
	@Override
	public Admin getAdmin(String userId) {
		LOGGER.info("begins getAdmin method by user id");
		Admin admin = adminDao.getAdmin(userId);
		Bank bank = bankService.getBank(101);
		if(admin != null)
			admin.setBank(bank);
		
		LOGGER.info("ends getAdmin method by user id");
		return admin;
	}

	@Override
	public Admin getAdmin(int adminId) {
		LOGGER.info("begins getAdmin method admin id");
		LOGGER.info("ends getAdmin method admin id");
		return adminDao.getAdmin(adminId);
	}

	@Override
	public void updateAdmin(Admin admin) {
		LOGGER.info("begins updateAdmin method");
		LOGGER.info("ends updateAdmin method");
		adminDao.updateAdmin(admin);

	}

	@Override
	public void insertAdmin(Admin admin) {
		LOGGER.info("begins insertAdmin method");
		LOGGER.info("ends insertAdmin method");
		adminDao.insertAdmin(admin);

	}

}
