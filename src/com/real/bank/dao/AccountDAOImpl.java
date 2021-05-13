package com.real.bank.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.real.bank.controller.Deposite;
import com.real.bank.model.Account;
import com.real.bank.util.DBConnectionProvider;

public class AccountDAOImpl implements AccountDAO {

	Connection con;
	private static final Logger LOGGER = Logger.getLogger(AccountDAOImpl.class);
	
	 
	@Override
	public int addAccount(Account account) {
		LOGGER.info("begins addAccount method");
		PreparedStatement st = null;
		String sql = "insert into account values(?,?,?,?,?,?,?,?,?,?,?,?)";
		int affectedRows = 0;

		
		
		try {
			con = DBConnectionProvider.getConntection();
			con.setAutoCommit(false);
			st = con.prepareStatement(sql);
			st.setInt(1, account.getAccountId());
			st.setString(2, account.getAccountType());
			st.setLong(3, account.getAccountNumber());
			st.setString(4, account.getUserId());
			st.setInt(5, account.getPin());
			st.setString(6, account.getDescription());
			st.setDouble(7, account.getBalance());
			st.setDouble(8, account.getBeginBalance());
			// st.setDate(9, new Date(account.getTimeStamp().getTime()));
			st.setTimestamp(9, new Timestamp(account.getTimeStamp().getTime()));
			st.setInt(10, account.getTransactionLimit());
			st.setDouble(11, account.getTransactionAmountLimit());
			st.setBoolean(12, account.isActive());
		

			
			affectedRows = st.executeUpdate();
			System.out.println("Affected rows i accout metod " + affectedRows);
			if (affectedRows > 0) {
				LOGGER.info("A new account enter in the database");
				//con.commit();
			}
		} catch (ClassNotFoundException | SQLException | java.io.IOException e) {
			affectedRows = 0;
			
			try {
				con.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
		
		LOGGER.info("ends addAccount method");
		return affectedRows;

	}

	@Override
	public Account getAccountInfo(int accountId) {
		LOGGER.info("begins getAccountInfo method");
		LOGGER.info("ends getAccountInfo method");
		return null;
	}

	

	@Override
	public int updateBalance(Account account) {
		LOGGER.info("begins updateBalance method");
		String sql = "update account set balance = ? where account_id = ?";
		PreparedStatement st = null;
		int affectedRows = 0;

		try {
			con = DBConnectionProvider.getConntection();
			//con.setAutoCommit(false);
			st = con.prepareStatement(sql);
			st.setDouble(1, account.getBalance());
			st.setInt(2, account.getAccountId());
			affectedRows = st.executeUpdate();
			LOGGER.info("account balance updated");
			/*
			 * if (affectedRows > 0) { con.commit(); }
			 */

		} catch (ClassNotFoundException | SQLException | IOException e) {
			LOGGER.info("Got exception" + e);
			/*
			 * try { con.rollback(); } catch (SQLException e1) { // TODO Auto-generated
			 * catch block e1.printStackTrace(); }
			 */
			e.printStackTrace();
		} finally {
			try {
				if(st != null)
					st.close();
				DBConnectionProvider.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		LOGGER.info("ends updateBalance method");
		return affectedRows;
	}

	@Override
	public Account getAccountInfo(long accountNumber) {
		LOGGER.info("begins getAccountInfo method");
		
		Account account = null;
		String sql = "select * from account where account_number = ?";
		PreparedStatement st = null;
		try {
			con = DBConnectionProvider.getConntection();
			st = con.prepareStatement(sql);
			st.setLong(1, accountNumber);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				account = new Account();
				account.setAccountId(rs.getInt(1));
				account.setAccountType(rs.getString(2));
				account.setAccountNumber(rs.getLong(3));
				account.setUserId(rs.getString(4));
				account.setPin(rs.getInt(5));
				account.setDescription(rs.getString(6));
				account.setBalance(rs.getDouble(7));
				account.setBeginBalance(rs.getDouble(8));
				account.setTimeStamp(rs.getTimestamp(9));
				account.setTransactionLimit(rs.getInt(10));
				account.setTransactionAmountLimit(rs.getDouble(11));
				LOGGER.info("Account retrived from database");
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			LOGGER.info("exception occured" + e);
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
				DBConnectionProvider.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		LOGGER.info("ends getAccountInfo method");
		return account;
	}

	@Override
	public Account getAccountInfo(String userId) {
		LOGGER.info("begins getAccountInfo method by user id");
		LOGGER.info("ends getAccountInfo method method by user id");
		Account account = null;
		String sql = "select * from account where user_id = ?";
		PreparedStatement st = null;

		try {
			con = DBConnectionProvider.getConntection();
			st = con.prepareStatement(sql);
			st.setString(1, userId);
			ResultSet rs = st.executeQuery();
			account = fill(rs, new Account());
			LOGGER.info("in getAccountInfo method method by user id account retrived");
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(st != null)
					st.close();
				DBConnectionProvider.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		LOGGER.info("ends getAccountInfo method method by user id");
		return account;
	}

	private Account fill(ResultSet rs, Account account) throws SQLException {
		LOGGER.info("begins fill method");
		if (rs.next()) {
			account = new Account();
			account.setAccountId(rs.getInt(1));
			account.setAccountType(rs.getString(2));
			account.setAccountNumber(rs.getLong(3));
			account.setUserId(rs.getString(4));
			account.setPin(rs.getInt(5));
			account.setDescription(rs.getString(6));
			account.setBalance(rs.getDouble(7));
			account.setBeginBalance(rs.getDouble(8));
			account.setTimeStamp(rs.getTimestamp(9));
			account.setTransactionLimit(rs.getInt(10));
			account.setTransactionAmountLimit(rs.getDouble(11));
			account.setActive(rs.getBoolean(12));
		}
		LOGGER.info("ends fill method");
		return account;
	}

	@Override
	public int resetUserId(Account account, String newUserId) {
		LOGGER.info("begins resetUserId method");
		String sql = "update account set user_id = ? where account_id = ?";
		PreparedStatement st = null;
		int affectedRows = 0;

		try {
			con = DBConnectionProvider.getConntection();
			st = con.prepareStatement(sql);
			st.setString(1, newUserId);
			st.setInt(2, account.getAccountId());
			affectedRows = st.executeUpdate();
			if(affectedRows > 0)
				LOGGER.info("user id has been reset user id: " + account.getUserId());
		} catch (ClassNotFoundException | SQLException | IOException e) {

			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
				DBConnectionProvider.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		LOGGER.info("ends resetUserId method");
		return affectedRows;

	}

	@Override
	public int resetPin(Account account) {
		LOGGER.info("begins resetPin method");

		String sql = "update account set pin = ? where account_id = ?";
		PreparedStatement st = null;
		int affectedRows = 0;

		try {
			con = DBConnectionProvider.getConntection();
			st = con.prepareStatement(sql);
			st.setInt(1, account.getPin());
			st.setInt(2, account.getAccountId());
			affectedRows = st.executeUpdate();

			if(affectedRows > 0)
			{
				LOGGER.info("pin has been reset");
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {

			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
				DBConnectionProvider.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		LOGGER.info("ends resetPin method");
		return affectedRows;

	
	}

	@Override
	public List<Account> getAllAccounts() {
		
		LOGGER.info("begins getAllAccounts method");
		List<Account> accounts = new ArrayList<>();
		String sq = "select * from account";
		
		Statement st = null;
		try {
			con = DBConnectionProvider.getConntection();
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sq);
			
			while(rs.next())
			{
				Account account = new Account();
				account.setAccountId(rs.getInt(1));
				account.setAccountType(rs.getString(2));
				account.setAccountNumber(rs.getLong(3));
				account.setUserId(rs.getString(4));
				account.setPin(rs.getInt(5));
				account.setDescription(rs.getString(6));
				account.setBalance(rs.getDouble(7));
				account.setBeginBalance(rs.getDouble(8));
				account.setTimeStamp(rs.getTimestamp(9));
				account.setTransactionLimit(rs.getInt(10));
				account.setTransactionAmountLimit(rs.getDouble(11));
				account.setActive(rs.getBoolean(12));
				
				accounts.add(account);
			}
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			
			LOGGER.info("exception occured in getAllAccounts method" + e);
		}
		finally {
			try {
				if(st != null)
					st.close();
				DBConnectionProvider.closeConnection();
			} catch (Exception e) {
				LOGGER.info("exception occured in getAllAccounts method" + e);
			}

		}
		
		LOGGER.info("ends getAllAccounts method");
		return accounts;
	}

	@Override
	public int activateAccount(long accountNumber) {
		LOGGER.info("begins activateAccount method");
		
		String sql = "update account set account_status = ? where account_number = ?";
		
		PreparedStatement st = null;
		int rowsAffected = 0;
		try {
			con = DBConnectionProvider.getConntection();
			st = con.prepareStatement(sql);
			
			st.setBoolean(1, true);
			st.setLong(2, accountNumber);
			
			rowsAffected = st.executeUpdate();
			 
			 
			 
		} catch (ClassNotFoundException | SQLException | IOException e) {
			
			LOGGER.info("exception in activateAccount method" + e);
		}
		finally
		{
			try {
				st.close();
				DBConnectionProvider.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.info("exception in activateAccount method" + e);
			}
			
			
		}
		
		LOGGER.info("ends activateAccount method");
		return rowsAffected;
	}

	@Override
	public int deactivateAccount(long accountNumber) {
		LOGGER.info("begins deactivateAccount method");
		
		String sql = "update account set account_status = ? where account_number = ?";
		
		PreparedStatement st = null;
		int rowsAffected = 0;
		try {
			con = DBConnectionProvider.getConntection();
			st = con.prepareStatement(sql);
			
			st.setBoolean(1, false);
			st.setLong(2, accountNumber);
			
			 rowsAffected = st.executeUpdate();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			 
			LOGGER.info("exception in deactivateAccount method" + e);
		}
		finally
		{
			try {
				st.close();
				DBConnectionProvider.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.info("exception in deactivateAccount method" + e);
			}
			
			
		}
		
		LOGGER.info("ends deactivateAccount method");
		return rowsAffected;
		
	}

	@Override
	public int deleteAccount(long accountNumber) {
		
		
		LOGGER.info("begins deleteAccount method");
		String sql = "delete from account where account_number = ?";
		
		PreparedStatement st = null;
		int rowsAffected = 0;
		try {
			con = DBConnectionProvider.getConntection();
			st = con.prepareStatement(sql);
			
			
			st.setLong(1, accountNumber);
			
			 rowsAffected = st.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			LOGGER.info(" exception in deleteAccount method" + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.info(" exception in deleteAccount method" + e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.info(" exception in deleteAccount method" + e);
		}
		
		LOGGER.info("ends deleteAccount method");
		return rowsAffected;
		
	}

	@Override
	public Account getAccountInfoBycustomerId(int customerId) {
		
		LOGGER.info("begins getAccountInfoBycustomerId method");
		
		Account account = null;
		String sql = "select * from account where account_id in (select account_id from customer where customer_id = ?)";
		PreparedStatement st = null;
		try {
			con = DBConnectionProvider.getConntection();
			st = con.prepareStatement(sql);
			st.setInt(1, customerId);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				account = new Account();
				account.setAccountId(rs.getInt(1));
				account.setAccountType(rs.getString(2));
				account.setAccountNumber(rs.getLong(3));
				account.setUserId(rs.getString(4));
				account.setPin(rs.getInt(5));
				account.setDescription(rs.getString(6));
				account.setBalance(rs.getDouble(7));
				account.setBeginBalance(rs.getDouble(8));
				account.setTimeStamp(rs.getTimestamp(9));
				account.setTransactionLimit(rs.getInt(10));
				account.setTransactionAmountLimit(rs.getDouble(11));
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {

			LOGGER.info("exception in getAccountInfoBycustomerId method" + e);
		} finally {
			try {
				if (st != null)
					st.close();
				DBConnectionProvider.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.info("exception in getAccountInfoBycustomerId method" + e);
			}
		}

		LOGGER.info("ends getAccountInfoBycustomerId method");
		return account;
	}
	
	

}
