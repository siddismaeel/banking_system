package com.real.bank.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.real.bank.model.Customer;
import com.real.bank.model.TX;
import com.real.bank.service.AccountServiceImpl;
import com.real.bank.util.DBConnectionProvider;

public class TransactionDAOImpl implements TransactionDAO {

	private static final Logger LOGGER = Logger.getLogger(TransactionDAOImpl.class);
	private Connection con;
	private AccountServiceImpl accountService = new AccountServiceImpl();
	
	

	@Override
	public TX getTransactionDetails(long txId) {
		LOGGER.info("begins getTransactionDetails method");
		String sql = "select * from tx where account_id=?";
		TX tx = null;
		PreparedStatement st = null;
		try {
			con = DBConnectionProvider.getConntection();
			st = con.prepareStatement(sql);
			
			st.setLong(1, txId);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				tx =new TX();
				
				tx.setTransactionId(rs.getLong(1));
				tx.setTimeStamp(rs.getTimestamp(2));
				tx.setAmount(rs.getDouble(3));
				tx.setBalance(rs.getDouble(4));
				tx.setDescription(rs.getString(5));
				
				
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in getTransactions method " + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in getTransactions method " + e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in getTransactions method " + e);
		}
		LOGGER.info("ends getTransactionDetails method");
		return tx;
	}

	@Override
	public List<TX> getTransactions(int accountId) {
		LOGGER.info("begins getTransactions method");
		List<TX> transactions = new ArrayList<>();
		String sql = "select * from tx where account_id=?";
		PreparedStatement st = null;
		try {
			con = DBConnectionProvider.getConntection();
			st = con.prepareStatement(sql);
			
			st.setInt(1, accountId);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				TX tx = new TX();
				
				tx.setTransactionId(rs.getLong(1));
				tx.setTimeStamp(rs.getTimestamp(2));
				tx.setAmount(rs.getDouble(3));
				tx.setBalance(rs.getDouble(4));
				tx.setDescription(rs.getString(5));
				
				transactions.add(tx);
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in getTransactions method " + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in getTransactions method " + e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in getTransactions method " + e);
		}
		
		LOGGER.info("ends getTransactions method");
		return transactions;
	}

	@Override
	public List<TX> getTransactions(int accountId, Date onDate) {
		
		LOGGER.info("begins getTransactions method by date and account id");
		LOGGER.info("ends getTransactions method by date and account id");
		return null;
	}

	@Override
	public List<TX> getTransactionsOfLast6Months(int accountId) {
		LOGGER.info("begins getTransactionsOfLast6Months method");
		
		List<TX> transactions = new ArrayList<>();
		Date date = Calendar.getInstance().getTime();
		long timeOf6months = 15552000000l;

		String sql = "select * from tx where account_id  = ? and time_stamp between ? and ?;";
		PreparedStatement st = null;
		try {
			con = DBConnectionProvider.getConntection();
			st = con.prepareStatement(sql);
			st.setInt(1, accountId);
			st.setTimestamp(2, new Timestamp(date.getTime()-timeOf6months));
			st.setTimestamp(3, new Timestamp(date.getTime()));
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				TX tx = new TX();
				tx.setTransactionId(rs.getInt(1));
				tx.setAmount(rs.getInt(3));
				tx.setTimeStamp(rs.getTimestamp(4));
				tx.setAmount(rs.getDouble(5));
				tx.setBalance(rs.getDouble(6));
				tx.setDescription(rs.getString(7));
				
				transactions.add(tx);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in getTransactionsOfLast6Months method " + e);
		}
		
		LOGGER.info("begins getTransactionsOfLast6Months method");
		return transactions;
	}

	@Override
	public double deposite(TX tx) {
		
		LOGGER.info("begins deposite method");
		String sql = "insert into tx values(?,?,?,?,?,?)";
		int affectedRows = 0;
		PreparedStatement st = null;
		
		try {
			con = DBConnectionProvider.getConntection();
			//con.setAutoCommit(false);
			st = con.prepareStatement(sql);
			st.setLong(1, tx.getTransactionId());
			st.setTimestamp(2, new Timestamp(tx.getTimeStamp().getTime()));
			st.setDouble(3,tx.getAmount());
			st.setDouble(4, tx.getAccount().getBalance() + tx.getAmount());
			st.setString(5, tx.getDescription());
			st.setInt(6, tx.getAccount().getAccountId());
			affectedRows = st.executeUpdate();
			
			if(affectedRows > 0)
			{
				tx.getAccount().setBalance(tx.getAmount() + tx.getAccount().getBalance());
				affectedRows =  accountService.updateBalance(tx.getAccount());
			}
			
			/*
			 * if(affectedRows > 0) { con.commit(); } else { con.rollback(); }
			 */
		} catch (ClassNotFoundException | SQLException | IOException e) {
			/*
			 * try { con.rollback(); } catch (SQLException e1) { // TODO Auto-generated
			 * catch block e1.printStackTrace(); }
			 * */
			LOGGER.info("exception in deposite method " + e);
			 
		}
		finally
		{
			try {
				if(st != null)
					st.close();
				DBConnectionProvider.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.info("exception in deposite method " + e);
			}
		}
		
		
		LOGGER.info("ends deposite method");
		return affectedRows;
	}

	@Override
	public double withdraw(TX tx) {

		LOGGER.info("begins withdraw method");

		
		String sql = "insert into tx values(?,?,?,?,?,?)";
		int affectedRows = 0;
		PreparedStatement st = null;
		
		try {
			con = DBConnectionProvider.getConntection();
			//con.setAutoCommit(false);
			st = con.prepareStatement(sql);
			st.setLong(1, tx.getTransactionId());
			st.setTimestamp(2, new Timestamp(tx.getTimeStamp().getTime()));
			
			st.setDouble(3,tx.getAmount());
			st.setDouble(4, tx.getAccount().getBalance() - tx.getAmount());
			st.setString(5, tx.getDescription());
			st.setInt(6, tx.getAccount().getAccountId());
			affectedRows = st.executeUpdate();
			
			if(affectedRows > 0)
			{
				tx.getAccount().setBalance(tx.getAccount().getBalance() - tx.getAmount());
				affectedRows =  accountService.updateBalance(tx.getAccount());
			}
			
			/*
			 * if(affectedRows > 0) { con.commit(); } else { con.rollback(); }
			 */
		} catch (ClassNotFoundException | SQLException | IOException e) {
			/*
			 * try { con.rollback(); } catch (SQLException e1) { // TODO Auto-generated
			 * catch block e1.printStackTrace(); }
			 */
			LOGGER.info("exception in withdraw method " + e);
		}
		finally
		{
			try {
				if(st != null)
					st.close();
				DBConnectionProvider.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.info("exception in withdraw method " + e);
			}
		}
		
		
		LOGGER.info("ends withdraw method");
		return affectedRows;
	
		
		
		
	}

	@Override
	public TX getTransactionDetails(Customer customer, long transactionId) {
		LOGGER.info("begins getTransactionDetails method by transactionId");
		String sql = "select * from tx where tx_id = ?";
		TX tx =null;
		PreparedStatement st = null;
		try {
			con = DBConnectionProvider.getConntection();
			st = con.prepareStatement(sql);
			st.setLong(1, transactionId);
			ResultSet rs = st.executeQuery();
			if(rs.next())
			{
				System.out.println("from transaction dao " + customer);
				tx = new TX();
				tx.setTransactionId(rs.getLong(1));
				tx.setAccount(customer.getAccount());
				Calendar cl = Calendar.getInstance();
				cl.setTimeInMillis(rs.getTimestamp(2).getTime());
				Date date = cl.getTime();
				
				tx.setTimeStamp(date);
				tx.setAmount(rs.getDouble("amount"));
				tx.setBalance(rs.getDouble(4));
				tx.setDescription(rs.getString(5));
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block getTimestamp(2).getTime())
			LOGGER.info("exception in getTransactionDetails method by transactionId " + e);
		} catch (SQLException e) { 
			// TODO Auto-generated catch block
			LOGGER.info("exception in getTransactionDetails method by transactionId " + e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in getTransactionDetails method by transactionId " + e);
		}
		catch(NullPointerException e)
		{
			LOGGER.info("exception in getTransactionDetails method by transactionId " + e);
		}
		LOGGER.info("begins getTransactionDetails method by transactionId");
		return tx;
	}

	@Override
	public List<TX> getTransactions(int accountId, Date fromDate, Date toDate) {
		
		LOGGER.info("begins getTransactions method by accountId, fromDate, toDate");
		
		String sql = "select * from tx where "
				+ " account_id = ? and time_stamp between ? and ?";
		
		
		List<TX> transactions = new ArrayList<>();
		PreparedStatement st = null;
		try {
			con = DBConnectionProvider.getConntection();
			st = con.prepareStatement(sql);
			st.setInt(1, accountId);
			st.setTimestamp(2, new Timestamp(fromDate.getTime()-15778800000l));
			st.setTimestamp(3, new Timestamp(toDate.getTime()));
			ResultSet rs = st.executeQuery();
		
			while(rs.next())
			{
				
				TX tx = new TX();
				tx.setTransactionId(rs.getLong(1));
				//tx.setAccount(customer.getAccount());
				tx.setTimeStamp(rs.getTimestamp(2));
				tx.setAmount(rs.getDouble(3));
				tx.setBalance(rs.getDouble(4));
				tx.setDescription(rs.getString(5));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in getTransactions method by accountId, fromDate, toDate " + e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in getTransactions method by accountId, fromDate, toDate " + e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in getTransactions method by accountId, fromDate, toDate " + e);
		}
		LOGGER.info("ends getTransactions method by accountId, fromDate, toDate");
		return transactions;
		
	}
	
	

}
