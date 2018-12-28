package com.sti.bootcamp.dao;

import java.util.List;

import com.sti.bootcamp.exception.MyCustomException;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Customer;

public interface AccountDao {
	Account getById(int id) throws MyCustomException;
	Account save(Account account) throws MyCustomException;
	void delete(Account account) throws MyCustomException;
	
	List<Account> getList() throws MyCustomException;
	List<Account> getListByCustomer(Customer customer) throws MyCustomException;
}