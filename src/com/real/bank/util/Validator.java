package com.real.bank.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class Validator {

	private static final Logger LOGGER = Logger.getLogger(Validator.class);
	public static boolean isValidEmail(String email) {

		LOGGER.info("begins isValidEmail method");
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		LOGGER.info("ends isValidEmail method");
		return matcher.matches();

	}

	public static boolean isValidateTransactionId(String transaction) {
		
		LOGGER.info("begins isValidateTransactionId method");
		boolean valid = false;

		String regex = "^[0-9]{12}$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(transaction);

		valid = matcher.matches();

		LOGGER.info("ends isValidateTransactionId method");
		return valid;
	}

	public static boolean isValidMobile(String mobileNumber) {
		
		LOGGER.info("begins isValidMobile method");
		boolean isValid = false;

		String regex = "^[6-9]{1}[0-9]{9}$";
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(mobileNumber);

		isValid = matcher.matches();
		LOGGER.info("ends isValidMobile method");
		return isValid;
	}

	public static boolean isValidUserId(String userId) {
		
		LOGGER.info("begins isValidUserId method");
		boolean isValid = false;
		
		String regex = "^(?i)^(?=.*[a-z])[a-z0-9]{6,20}$";
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(userId);

		isValid = matcher.matches();
		LOGGER.info("ends isValidUserId method");
		return true;
	}

	

}
