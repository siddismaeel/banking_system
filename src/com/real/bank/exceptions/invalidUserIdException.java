package com.real.bank.exceptions;

public class invalidUserIdException extends Exception {

	@Override
	public String getMessage() {
		
		return "User ID is invalid";
	}
	
	

}
