package com.customer.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.biz.domain.Customer;
import com.customer.biz.exception.DataNotFoundException;
import com.customer.biz.service.CustomerService;
import com.customer.dao.mapper.CustomerDAO;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDAO customerDAO;
	
	@Override
	public List<Customer> findAll() {
		return customerDAO.selectAllCustomer();
	}

	@Override
	public Customer findById(int id) throws DataNotFoundException {
		Customer customer = customerDAO.selectCustomer(id);
		if (customer == null) {
			throw new DataNotFoundException();
		}
		
		return customer;
	}

	@Override
	public void register(Customer customer) {
		customerDAO.insertCustomer(customer);
	}

	@Override
	public void update(Customer customer) throws DataNotFoundException {
		customerDAO.updateCustomer(customer);
	}

	@Override
	public void delete(int id) throws DataNotFoundException {
		customerDAO.deleteCustomer(id);
	}

	@Override
	public boolean isFreeEmailCustomer(Customer customer) {
		return customerDAO.selectCustomer(customer.getId()).isFreeEmail();
	}

}
