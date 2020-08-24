package com.customer.dao.mapper;

import java.util.List;

import com.customer.biz.domain.Customer;

public interface CustomerDAO {
	void insertCustomer(Customer customer);
	
	Customer selectCustomer(int id);
	
	void updateCustomer(Customer customer);
	
	void deleteCustomer(int id);
	
	List<Customer> selectAllCustomer();
	
	int selectCount(String tableName);
}
