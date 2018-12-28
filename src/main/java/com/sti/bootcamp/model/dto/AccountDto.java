package com.sti.bootcamp.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sti.bootcamp.model.Customer;

public class AccountDto {
	private int accountnumber;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date opendate;
	private int balance;
	private Customer customer;
	
	public AccountDto() {}
	
	public AccountDto(int accountnumber, Date opendate, int balance, Customer customer) {
		this.accountnumber = accountnumber;
		this.opendate = opendate;
		this.balance = balance;
		this.customer = customer;
	}

	public int getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(int accountnumber) {
		this.accountnumber = accountnumber;
	}

	public Date getOpendate() {
		return opendate;
	}

	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
