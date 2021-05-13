package com.real.bank.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.real.bank.exceptions.InvalidEmailException;
import com.real.bank.exceptions.InvalidMobileNumberException;
import com.real.bank.model.Customer;
import com.real.bank.service.AccountService;
import com.real.bank.service.AccountServiceImpl;
import com.real.bank.util.DBConnectionProvider;

public class CustomerDAOImpl implements CustomerDAO {
	private Connection con;
	private static final Logger LOGGER = Logger.getLogger(CustomerDAOImpl.class);
	AccountDAO accountDao = new AccountDAOImpl();
	AccountService accountService = new AccountServiceImpl();

	@Override
	public int addCustomer(Customer customer) {
		LOGGER.info("begins addCustomer method");
		PreparedStatement st = null;
		String sql = "insert into customer values(?,?,?,?,?,?,?,?,?,?)";
		int affectedRows = 0;

		try {
			con = DBConnectionProvider.getConntection();
			con.setAutoCommit(false);
			st = con.prepareStatement(sql);
			st.setInt(1, customer.getCutomerId());
			st.setString(2, customer.getFirstName());
			st.setString(3, customer.getLastName());
			st.setString(4, customer.getStreet());
			st.setString(5, customer.getCity());
			st.setString(6, customer.getState());
			st.setInt(7, customer.getPin());
			st.setString(8, customer.getContact());
			st.setString(9, customer.getEmail());
			st.setInt(10, customer.getAccount().getAccountId());

			affectedRows = accountService.addAccount(customer.getAccount());
			
			

			if (affectedRows > 0)
			{
				affectedRows = st.executeUpdate();
			}
			if(affectedRows > 0)
			{
				con.commit();
			}
			else
			{
				con.rollback();
			}
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			affectedRows  = 0;
			try {
				con.rollback();
			} catch (SQLException e1) {

				LOGGER.info("exception in addCustomer method " + e);
			}
			LOGGER.info("exception in addCustomer method " + e);
		}
		finally {
			try {
				if (st != null)
					st.close();
				DBConnectionProvider.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.info("exception in addCustomer method " + e);
			}
		}

		LOGGER.info("ends addCustomer method");
		return affectedRows;
	}

	@Override
	public Customer getCustomerInfo(int id) {
		LOGGER.info("begins getCustomerInfo method by id");
		PreparedStatement st = null;
		String sql = " select * from customer c where c.customer_id = ?";
		Customer customer = null;

		try {
			con = DBConnectionProvider.getConntection();
			
			st = con.prepareStatement(sql);
			st.setInt(1,id);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				customer = new Customer();
				customer.setCutomerId(rs.getInt(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				customer.setStreet(rs.getString(4));
				customer.setCity(rs.getString(5));
				customer.setState(rs.getString(6));
				customer.setPin(rs.getInt(7));
				
				
				
				try {
					customer.setContact(rs.getString(8));
					customer.setEmail(rs.getString(9));
				} catch (InvalidEmailException e) {
					// TODO Auto-generated catch block
					LOGGER.info("exception in getCustomerInfo method " + e);
				} catch (InvalidMobileNumberException e) {
					// TODO Auto-generated catch block
					LOGGER.info("exception in getCustomerInfo method " + e);
				}
				customer.setAccount(accountDao.getAccountInfoBycustomerId(id));

			}

		} catch (ClassNotFoundException | SQLException | IOException e) {
			
			LOGGER.info("exception in getCustomerInfo method by id " + e);
		}
		finally {
			try {
				if (st != null)
					st.close();
				DBConnectionProvider.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.info("exception in getCustomerInfo method by id" + e);
			}
		}

		LOGGER.info("ends getCustomerInfo method by id");
		return customer;
	}

	@Override
	public Customer getCustomerInfo(long accountNumber){
		
		LOGGER.info("beins getCustomerInfo method by account number");
		
		PreparedStatement st = null;
		String sql = " select * from customer c where c.account_id in(select account_id from account a where account_number = ?)";
		Customer customer = null;

		try {
			con = DBConnectionProvider.getConntection();
			con.setAutoCommit(false);
			st = con.prepareStatement(sql);
			st.setLong(1, accountNumber);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				customer = new Customer();
				customer.setCutomerId(rs.getInt(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				customer.setStreet(rs.getString(4));
				customer.setCity(rs.getString(5));
				customer.setState(rs.getString(6));
				customer.setPin(rs.getInt(7));
				
				
				
				try {
					customer.setContact(rs.getString(8));
					customer.setEmail(rs.getString(9));
				} catch (InvalidEmailException e) {
					// TODO Auto-generated catch block
					LOGGER.info("exception in getCustomerInfo method by account number " + e);
				} catch (InvalidMobileNumberException e) {
					// TODO Auto-generated catch block
					LOGGER.info("exception in getCustomerInfo method by account number " + e);
				}
				customer.setAccount(accountDao.getAccountInfo(accountNumber));

			}

		} catch (ClassNotFoundException | SQLException | IOException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {

				LOGGER.info("exception in getCustomerInfo method by account number " + e1);
			}
			LOGGER.info("exception in getCustomerInfo method by account number " + e);
		}
		finally {
			try {
				if (st != null)
					st.close();
				DBConnectionProvider.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.info("exception in getCustomerInfo method by account number " + e);
			}
		}

		LOGGER.info("ends getCustomerInfo method by account number");
		return customer;
	}

	@Override
	public Customer getCustomerInfo(String userId) {
		LOGGER.info("begins getCustomerInfo method by user id");
		
		String sql = "select * from customer where account_id in (select account_id from account where user_id = '" +userId+ "')";
		Customer customer = null;
		Statement  st = null;
		
		try {
			con = DBConnectionProvider.getConntection();
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			
			if (rs.next()) {
				customer = new Customer();
				customer.setCutomerId(rs.getInt(1));
				customer.setFirstName(rs.getString(2));
				customer.setLastName(rs.getString(3));
				customer.setStreet(rs.getString(4));
				customer.setCity(rs.getString(5));
				customer.setState(rs.getString(6));
				customer.setPin(rs.getInt(7));
				try {
					customer.setContact(rs.getString(8));
					customer.setEmail(rs.getString(9));
				} catch (InvalidMobileNumberException | InvalidEmailException e) {
					// TODO Auto-generated catch block
					LOGGER.info("exception in getCustomerInfo method by user id " + e);
				}
				
				//customer.setAccount(accountDao.getAccountInfo(userId));

			}
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			LOGGER.info("exception in getCustomerInfo method by user id " + e);
		}
		finally {
			try {
				if (st != null)
					st.close();
				DBConnectionProvider.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				LOGGER.info("exception in getCustomerInfo method by user id " + e);
			}
		}
		
		LOGGER.info("ends getCustomerInfo method by user id");
		return customer;
	}

	
}
