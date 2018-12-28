package com.sti.bootcamp.dao;

import java.util.List;

import com.sti.bootcamp.exception.MyCustomException;
import com.sti.bootcamp.model.Customer;

public interface CustomerDao {
	
	Customer getById(int id) throws MyCustomException;
	Customer save(Customer customer) throws MyCustomException;
	void delete(Customer customer) throws MyCustomException;
	
	List<Customer> getList() throws MyCustomException;


}
