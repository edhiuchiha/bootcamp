package com.sti.bootcamp.dao;

import java.util.List;

import com.sti.bootcamp.exception.MyCustomException;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Transaction;

public interface TransactionDao {
	Transaction getById(int id) throws MyCustomException;
	Transaction save(Transaction transaction) throws MyCustomException;
	void delete(Transaction transaction) throws MyCustomException;
	
	List<Transaction> getList() throws MyCustomException;
	List<Transaction> getListByAccountnumber(Account account) throws MyCustomException;

}
