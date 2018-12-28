package com.sti.bootcamp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int transactionnumber;
	@Column
	private String type;
	@Column
	private String amount;
	@Column
	private String amountsign;
	@ManyToOne
	@JoinColumn(name="accountnumber")
	private Account accountnumber;
	
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
