package com.real.bank.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.real.bank.model.Bank;
import com.real.bank.util.DBConnectionProvider;

public class BankDaoImpl implements BankDao {

	Connection con = null;
	private static final Logger LOGGER = Logger.getLogger(BankDaoImpl.class);
	@Override
	public Bank getBank(int bankId) {
		LOGGER.info("begins getBank method");
		String sql = "select * from bank where bank_id = ?";
		Bank bank = null;
		PreparedStatement st = null;
		try {
			con = DBConnectionProvider.getConntection();
			st = con.prepareStatement(sql);
			st.setInt(1, bankId);
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
			{
				bank = new Bank();
				bank.setBankId(rs.getInt(1));
				bank.setBankName(rs.getString(2));
				bank.setBankAddress(rs.getString(3));
			}
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in getBank method " + e);
		}
		
		LOGGER.info("ends getBank method");
		return bank;
	}

	@Override
	public List<Bank> getAllBanks() {
		LOGGER.info("begins getAllBanks method");
		LOGGER.info("ends getAllBanks method");
		return null;
	}

	@Override
	public void updateBank(Bank bank) {
		LOGGER.info("begins updateBank method");
		LOGGER.info("ends updateBank method");

	}

	@Override
	public void insertBank(Bank bank) {
		LOGGER.info("begins insertBank method");
		LOGGER.info("ends insertBank method");

	}

}
