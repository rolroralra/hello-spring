package com.customer.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.customer.biz.domain.Customer;
import com.customer.biz.exception.DataNotFoundException;
import com.customer.biz.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerContoller {
	private Logger LOGGER = LoggerFactory.getLogger(CustomerContoller.class);
	
	@Autowired
	CustomerService customerService;

	@RequestMapping(method=GET)
	public String home() {
		return "forward:/customer/list";
	}
	
	@RequestMapping(value="/validate")
	public String validate(@Valid Customer customer, Errors errors) {
		if (errors.hasErrors()) {
			return "validateErrorPage";
		}
		
		return "validateOkPage";
	}
	
	@RequestMapping(value="/list", method=GET)
	public String showAllCustomers(Model model) {
		List<Customer> customers = customerService.findAll();
		model.addAttribute("customers", customers);
		
		return "customer/list";
	}
	
	@RequestMapping(value="/{customerId}", method=GET)
	public String showCustomerDetail(@PathVariable int customerId, Model model, HttpServletRequest request) throws DataNotFoundException {
		Customer customer = customerService.findById(customerId);
		model.addAttribute("customer", customer);
		
		return "customer/detail";
	}
	
	@ExceptionHandler(DataNotFoundException.class)
	public String handleException(DataNotFoundException e) {
		LOGGER.info("Customer is not found. " + e.getMessage());
		return "customer/notfound";
	}
}
