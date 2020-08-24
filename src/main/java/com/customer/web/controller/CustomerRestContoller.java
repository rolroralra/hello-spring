package com.customer.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.customer.biz.domain.Customer;
import com.customer.biz.exception.DataNotFoundException;
import com.customer.biz.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestContoller {
	private Logger LOGGER = LoggerFactory.getLogger(CustomerRestContoller.class);
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping(method=POST)
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public String register(@RequestBody Customer customer) {
		LOGGER.info(customer.toString());
		customerService.register(customer);
		return "OK";
	}
	
//	@RequestMapping(value="/{customerId}", method=GET)
//	@ResponseStatus(value=HttpStatus.OK)
//	@ResponseBody
//	public Customer findById(@PathVariable int customerId) throws DataNotFoundException {
//		Customer customer = customerService.findById(customerId);
//		LOGGER.info(customer.toString());
//		return customer;
//	}
	
	@RequestMapping(value="/{customerId}", method=GET)
	public ResponseEntity<Customer> findById(@PathVariable int customerId) throws DataNotFoundException {
		Customer customer = customerService.findById(customerId);
		LOGGER.info(customer.toString());
		
		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(new MediaType("text", "xml", Charset.forName("UTF-8")));
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		headers.set("My-Header", "MyHeaderValue");
		
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(MediaType.APPLICATION_JSON_UTF8);
		headers.setAccept(list);
		
		return new ResponseEntity<Customer>(customer, headers, HttpStatus.OK);
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleException() {
		return "custoemer is not found.";
	}
}
