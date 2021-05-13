package com.real.bank.exceptions;

public class TransactionLimitException extends Exception {

	int limit;

	public TransactionLimitException(int limit) {
		super();
		this.limit = limit;
	}

	@Override
	public String getMessage() {
		
		return "Transaction limit "+limit+ " exhasted";
	}
	
	
	
}
