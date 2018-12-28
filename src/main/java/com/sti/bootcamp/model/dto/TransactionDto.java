package com.sti.bootcamp.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sti.bootcamp.model.Account;

public class TransactionDto {
	private int transactionnumber;
	private String type;
	private String amount;
	private String amountsign;
	@JsonProperty("account")
	private Account accountnumber;
	
	public TransactionDto() {}
	
	public TransactionDto(int transactionnumber, String type, String amount, String amountsign, Account accountnumber) {
		this.transactionnumber = transactionnumber;
		this.type = type;
		this.amount = amount;
		this.amountsign = amountsign;
		this.accountnumber = accountnumber;
	}

	public int getTransactionnumber() {
		return transactionnumber;
	}

	public void setTransactionnumber(int transactionnumber) {
		this.transactionnumber = transactionnumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmountsign() {
		return amountsign;
	}

	public void setAmountsign(String amountsign) {
		this.amountsign = amountsign;
	}

	public Account getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(Account accountnumber) {
		this.accountnumber = accountnumber;
	}

}
