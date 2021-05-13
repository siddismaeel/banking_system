package com.real.bank.util;

import java.util.Random;

import org.apache.log4j.Logger;

import com.real.bank.dao.IDHandlerDAO;
import com.real.bank.dao.IDHandlerDaoImpl;

public class IDGenerator {

	private static int currentCustomerId;
	private static int currentAccountId;
	private static long currentAccountNumber;
	private static IDHandlerDAO idHandlerDAO = new IDHandlerDaoImpl();
	private static final Logger LOGGER = Logger.getLogger(IDGenerator.class);
	static {
		LOGGER.info("static block begins executing");
		IDGenerator.currentCustomerId = idHandlerDAO.getHighestCustomerId();
		IDGenerator.currentAccountId = idHandlerDAO.getHighestAccountId();
		IDGenerator.currentAccountNumber = idHandlerDAO.getHighestAccountNumber();
		LOGGER.info("static block ends executing");
	}

	public long generateId() {
		
		return 0;
	}

	public static long generateTransactionId() {

		LOGGER.info("begins generateTransactionId method");
		int length = 12;
		String transactionId = "";
		int[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		Random rnd = new Random();

		for (int i = 0; i < length; i++) {

			transactionId += values[rnd.nextInt(values.length)];

		}

		LOGGER.info("ends generateTransactionId method");
		return Long.parseLong(transactionId);
	}

	public static String generateUserId()
	{
		LOGGER.info("begins generateUserId method");
		String userId= "UID";
		Random rand = new Random();
		String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
		
		for(int i = 0; i< 8; i++)
		{
			userId += chars.charAt(rand.nextInt(chars.length()));
		}
		
		LOGGER.info("ends generateUserId method");
		return userId;
	}
	
	public static int generatePin()
	{
		LOGGER.info("begins generatePin method");
		Random rand = new Random();
		
		String chars = "0123456789";
		String pinDigists = "";
		
		for(int i =0; i< 6; i++)
		{
			pinDigists += chars.charAt(rand.nextInt(chars.length()));
		}
		LOGGER.info("ends generatePin method");
		return Integer.parseInt(pinDigists);
	}
	
	public static int generateOTP() {

		LOGGER.info("begins generateOTP method");
		int[] values = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int len = 6;

		Random rnd = new Random();

		String otp = "";

		for (int i = 0; i < len; i++) {

			otp += values[(rnd.nextInt(values.length))];

		}

		LOGGER.info("ends generateOTP method");
		return Integer.parseInt(otp);
	}

	public static int getCustomerId() {
		LOGGER.info("begins getCustomerId method");
		currentCustomerId += 1;
		LOGGER.info("ends getCustomerId method");

		return currentCustomerId;
	}

	public static int getAccountId() {
		LOGGER.info("begins getAccountId method");

		currentAccountId += 1;
		LOGGER.info("ends getAccountId method");
		return currentAccountId;
	}

	public static long getAccountNumber() {
		LOGGER.info("begins getAccountNumber method");
		currentAccountNumber += 1;
		LOGGER.info("ends getAccountNumber method");

		return currentAccountNumber;
	}
	
	
}
