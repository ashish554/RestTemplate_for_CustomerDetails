package com.spring.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.spring.model.Customer;

@Component
public class CustomerService {

	public Customer getCustomer(Integer customerId) {
		String URL="http://localhost:8080/home/customers/{customerId}";
		RestTemplate template= new RestTemplate();
		Customer customer= template.getForObject(URL, Customer.class, customerId);
		return customer;
	}

	public String addCustomer(Customer customer) {
		String URL="http://localhost:8080/home/customers";
		RestTemplate template= new RestTemplate();
		String response= template.postForObject(URL, customer, String.class);
		return response;
		
	}

	public List<Customer> getAllCustomers() {
		String URL="http://localhost:8080/home/customers";
		RestTemplate template= new RestTemplate();
		ResponseEntity<Customer[]> response= template.getForEntity(URL, Customer[].class);
		Customer[] list=response.getBody();
		return Arrays.asList(list);
	}

	public void updateCustomer(Integer customerId, Customer customer) {
		String URL="http://localhost:8080/home/customers/{customerId}";
		RestTemplate template= new RestTemplate();
		template.put(URL, customer, customerId);
		
	}

	public void deleteCustomer(Integer customerId) {
		String URL="http://localhost:8080/home/customers/{customerId}";
		RestTemplate template= new RestTemplate();
		template.delete(URL, customerId);
	}

}
