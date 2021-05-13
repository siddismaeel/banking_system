package com.real.bank.exceptions;

public class InvalidEmailException extends Exception {
 
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Email id is invalid";
	}

}
