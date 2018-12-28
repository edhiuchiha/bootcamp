package com.sti.bootcamp.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.bootcamp.dao.TransactionDao;
import com.sti.bootcamp.dao.repository.TransactionRepository;
import com.sti.bootcamp.exception.MyCustomException;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Transaction;

public class TransactionDaoImpl extends BaseImpl implements TransactionDao{
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Transaction getById(int id) throws MyCustomException {
		return transactionRepository.findOne(id);
	}

	@Override
	public Transaction save(Transaction transaction) throws MyCustomException {
		return transactionRepository.save(transaction);
	}

	@Override
	public void delete(Transaction transaction) throws MyCustomException {
		transactionRepository.delete(transaction);
		
	}

	@Override
	public List<Transaction> getList() throws MyCustomException {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = critB.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		query.select(root);
		
		TypedQuery<Transaction> q = em.createQuery(query);
		
		return q.getResultList();
	}

	@Override
	public List<Transaction> getListByAccountnumber(Account account) throws MyCustomException {
		return transactionRepository.findByAccountnumber(account);
	}

}
