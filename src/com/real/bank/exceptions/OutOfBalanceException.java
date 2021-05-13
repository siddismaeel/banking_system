package com.real.bank.exceptions;

public class OutOfBalanceException extends Exception {

	double amount;
	double balance;
	public OutOfBalanceException(double amount, double balance) {
		super();
		this.amount = amount;
		this.balance = balance;
	}
	@Override
	public String getMessage() {
		
		return balance - amount +"";
	}
	
	
	
	
}
