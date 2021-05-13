package com.real.bank.exceptions;

public class NegativeBalanceException extends Exception {

	@Override
	public String getMessage() {
		
		return "Balance should not be negative.";
	}

}
