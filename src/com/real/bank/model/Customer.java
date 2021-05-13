package com.real.bank.model;

import com.real.bank.exceptions.InvalidEmailException;
import com.real.bank.exceptions.InvalidMobileNumberException;
import com.real.bank.util.Validator;

public class Customer {

	
	private int cutomerId;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String state;
	private int pin;
	private String contact;
	private String email;
	private Account account;
	public int getCutomerId() {
		return cutomerId;
	}
	public void setCutomerId(int cutomerId) {
		this.cutomerId = cutomerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) throws InvalidMobileNumberException {
		
		if(!Validator.isValidMobile(contact))
		{
			throw new InvalidMobileNumberException();
		}
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) throws InvalidEmailException {
		
		if(!Validator.isValidEmail(email))
		{
			throw new InvalidEmailException();
		}
		this.email = email;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	 
	@Override
	public String toString() {
		return "Customer [cutomerId=" + cutomerId + ", firstName=" + firstName + ", lastName=" + lastName + ", street="
				+ street + ", city=" + city + ", state=" + state + ", pin=" + pin + ", contact=" + contact + ", emil="
				+ email + ", account=" + account + "]";
	}
	
	
	
	
}
