package com.real.bank.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.real.bank.util.DBConnectionProvider;

public class IDHandlerDaoImpl implements IDHandlerDAO {

	private static final Logger LOGGER = Logger.getLogger(IDHandlerDaoImpl.class);
	private Connection con;

	@Override
	public int getHighestCustomerId() {
		LOGGER.info("begins getHighestCustomerId method");
		Statement st = null;
		int customerId = 1;
		int highestId = 0;
		ResultSet rs = null;
		String sql = "select max(customer_id) from customer";

		try {
			con = DBConnectionProvider.getConntection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next())
				highestId = rs.getInt(1);
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			// TODO Auto-generated catch block
			LOGGER.info("exeption in getHighestCustomerId method " +e1);
		}

		finally {
			try {
				DBConnectionProvider.closeConnection();
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();

			} catch (SQLException e) {

				LOGGER.info("exeption in getHighestCustomerId method " +e);
			}
		}

		if (customerId < highestId)
			customerId = highestId;

		LOGGER.info("ends getHighestCustomerId method");
		return customerId;
	}

	@Override
	public int getHighestAccountId() {
		LOGGER.info("begins getHighestAccountId method");
		
		Statement st = null;
		int accountId = 1;
		int highestId = 0;
		ResultSet rs = null;
		String sql = "select max(account_id) from account";

		try {
			con = DBConnectionProvider.getConntection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next())
				highestId = rs.getInt(1);
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in getHighestAccountId method " + e1);
		} finally {
			try {
				DBConnectionProvider.closeConnection();
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();

			} catch (SQLException e) {

				LOGGER.info("exception in getHighestAccountId method " + e);
			}

		}

		if (accountId < highestId)
			accountId = highestId;

		LOGGER.info("ends getHighestAccountId method");
		return accountId;
	}

	@Override
	public long getHighestAccountNumber() {
		LOGGER.info("begins getHighestAccountNumber method");
		
		Statement st = null;
		long highestId = 0;
		long accountNumber = 1000l;
		ResultSet rs = null;
		String sql = "select max(account_number) from account";

		try {
			con = DBConnectionProvider.getConntection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next())
				highestId = rs.getLong(1);
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in  getHighestAccountNumber method " + e1);
		}

		finally {
			try {
				DBConnectionProvider.closeConnection();
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();

			} catch (SQLException e) {

				LOGGER.info("exception in  getHighestAccountNumber method " + e);
			}
		}

		if (accountNumber < highestId)
			accountNumber = highestId;

		LOGGER.info("ends getHighestAccountNumber method");
		return accountNumber;
	}
	

}
