package com.real.bank.exceptions;

public class InvalidTransactionIdException extends Exception {

	@Override
	public String getMessage() {
		
		return "Transaction id is invalid!";
	}

}
