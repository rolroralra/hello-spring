package com.customer.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.customer.biz.domain.Customer;
import com.customer.dao.mapper.CustomerDAO;

import lombok.Setter;

@Repository
@Setter
public class NPJdbcTemplateCustomerDAO implements CustomerDAO {
	@Autowired
	NamedParameterJdbcTemplate npJdbcTemplate;
	
	
	@Override
	public void insertCustomer(Customer customer) {
		npJdbcTemplate.update(
				"INSERT INTO CUSTOMER(ID, NAME, ADDRESS, EMAIL_ADDRESS) VALUES(:id, :name, :address, :emailAddress)", 
				new BeanPropertySqlParameterSource(customer)
		);
	}

	@Override
	public Customer selectCustomer(int id) {
		return npJdbcTemplate.query(
				"SELECT * FROM CUSTOMER WHERE ID = :id",
				new MapSqlParameterSource("id", id),
				new ResultSetExtractor<Customer>() {
					@Override
					public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
						if (!rs.next()) {
							return null;
						}
						
						Customer result = new Customer();
						result.setId(rs.getInt("ID"));
						result.setName(rs.getString("NAME"));
						result.setAddress(rs.getString("ADDRESS"));
						result.setEmailAddress(rs.getString("EMAIL_ADDRESS"));
						return result;
					}
				}
		);
	}

	@Override
	public void updateCustomer(Customer customer) {
		npJdbcTemplate.update(
				"UPDATE CUSTOMER SET NAME = :name, ADDRESS = :address, EMAIL_ADDRESS = :emailAddress WHERE ID = :id",
				new BeanPropertySqlParameterSource(customer)
		);
	}

	@Override
	public void deleteCustomer(int id) {
		npJdbcTemplate.update(
				"DELETE FROM CUSTOMER WHERE ID = :id",
				new MapSqlParameterSource("id", id)
		);
	}

	@Override
	public List<Customer> selectAllCustomer() {
		return npJdbcTemplate.query(
				"SELECT * FROM CUSTOMER", 
				new BeanPropertyRowMapper<Customer>(Customer.class)
		);
	}
	
	@Override
	public int selectCount(String tableName) {
		return npJdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM :TABLE_NAME", 
				new MapSqlParameterSource("TABLE_NAME", tableName),
				Integer.class
		); 
		
	}
}
