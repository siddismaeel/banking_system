package com.real.bank.model;

import java.util.Date;

public class Account {

	private int accountId;
	private String accountType;
	private long accountNumber;
	private String userId;
	private int pin;
	private String description;
	private double balance;
	private double beginBalance;
	private Date timeStamp;
	private int transactionLimit;
	private double transactionAmountLimit;
	private boolean active;
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getBeginBalance() {
		return beginBalance;
	}

	public void setBeginBalance(double beginBalance) {
		this.beginBalance = beginBalance;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getTransactionLimit() {
		return transactionLimit;
	}

	public void setTransactionLimit(int transactionLimit) {
		this.transactionLimit = transactionLimit;
	}

	public double getTransactionAmountLimit() {
		return transactionAmountLimit;
	}

	public void setTransactionAmountLimit(double transactionAmountLimit) {
		this.transactionAmountLimit = transactionAmountLimit;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountType=" + accountType + ", accountNumber=" + accountNumber
				+ ", userId=" + userId + ", pin=" + pin + ", description=" + description + ", balance=" + balance
				+ ", beginBalance=" + beginBalance + ", timeStamp=" + timeStamp + ", transactionLimit="
				+ transactionLimit + ", transactionAmountLimit=" + transactionAmountLimit + ", active=" + active + "]";
	}

	

	
}
