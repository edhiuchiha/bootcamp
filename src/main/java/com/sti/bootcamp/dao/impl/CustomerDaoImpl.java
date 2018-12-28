package com.sti.bootcamp.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import com.sti.bootcamp.dao.CustomerDao;
import com.sti.bootcamp.dao.repository.CustomerRepository;
import com.sti.bootcamp.exception.MyCustomException;
import com.sti.bootcamp.model.Customer;

public class CustomerDaoImpl extends BaseImpl implements CustomerDao {
	
	@Autowired
	private CustomerRepository repository;

	@Override
	public Customer getById(int id) throws MyCustomException {
		return repository.findOne(id);
	}

	@Override
	public Customer save(Customer customer) throws MyCustomException {
		return repository.save(customer);
	}

	@Override
	public void delete(Customer customer) throws MyCustomException {
		repository.delete(customer);
	}

	@Override
	public List<Customer> getList() throws MyCustomException {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Customer> query = critB.createQuery(Customer.class);
		Root<Customer> root = query.from(Customer.class);
		query.select(root);
		
		TypedQuery<Customer> q = em.createQuery(query);
		
		return q.getResultList();
	}

}
