package com.spring.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.spring.model.Customer;
import com.spring.services.CustomerService;

@RestController()
@RequestMapping(value="/template")
public class RestTemplateController {

	@Autowired
	CustomerService custmerservice;
	
	@Autowired
	Environment environment;
	
	@GetMapping(value="/customers/{customerId}")
	public ResponseEntity<Customer> getCustomerDetails(@PathVariable Integer customerId) throws Exception
	{
		try {
		Customer customer=custmerservice.getCustomer(customerId);	
		ResponseEntity<Customer> response=new ResponseEntity<Customer>(customer,HttpStatus.OK);
		return response;
		}catch(Exception e)
		{
			throw new  ResponseStatusException(HttpStatus.NOT_FOUND,environment.getProperty(e.getMessage()),e);
		}
	}
	
	
	@PostMapping(value="/customers")
	public ResponseEntity<String> addCustomers(@RequestBody Customer customer) throws Exception
	{
		try {
		String result=custmerservice.addCustomer(customer);
	    String message;
		if(result.equalsIgnoreCase("Added Successfully"))
	    {
		 message="Added Successfully";
	    }else
	    	message="Not added";
		return new ResponseEntity<String>(message,HttpStatus.CREATED);
		}catch(Exception e)
		{
			throw new  ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		}
		
	}
	
	@GetMapping(value="/customers")
	public ResponseEntity<List<Customer>> getAllCustometrs() throws Exception
	{
		List<Customer> customerList=custmerservice.getAllCustomers();
		ResponseEntity<List<Customer>> response=new ResponseEntity<List<Customer>>(customerList,HttpStatus.OK);
		return response;
		
	}
	
	@PutMapping(value="/customers/{customerId}")
	public ResponseEntity<String> updateCustomers(@PathVariable Integer customerId, @RequestBody Customer customer) throws Exception
	{
		custmerservice.updateCustomer(customerId,customer);
		String message="Updated Successfully";
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping(value="/customers/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerId) throws Exception
	{
		custmerservice.deleteCustomer(customerId);
		String message="Deleted Successfully";
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
	}
}
