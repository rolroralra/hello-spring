package com.customer.biz.service;

import java.util.List;

import com.customer.biz.domain.Customer;
import com.customer.biz.exception.DataNotFoundException;

public interface CustomerService {
	List<Customer> findAll();
	Customer findById(int id) throws DataNotFoundException;
	void register(Customer customer);
	void update(Customer customer) throws DataNotFoundException;
	void delete(int id) throws DataNotFoundException;
	boolean isFreeEmailCustomer(Customer customer);
}
