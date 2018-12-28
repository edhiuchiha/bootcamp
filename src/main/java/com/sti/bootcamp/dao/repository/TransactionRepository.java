package com.sti.bootcamp.dao.repository;

import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Transaction;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Integer>{
	
	Transaction findByTransactionnumber(int transactionnumber);
//	List<Transaction> findByAccountnumber(Account account);
	List<Transaction> findByAccountnumber(Account account);

}
