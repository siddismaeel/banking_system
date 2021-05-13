package com.real.bank.exceptions;

public class InvalidMobileNumberException extends Exception {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Mobile number is not valid";
	}

	
}
