package com.MBD.CabBooking.Controller;

import java.util.List;

import javax.print.event.PrintJobAttributeEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MBD.CabBooking.Entity.Customer;
import com.MBD.CabBooking.Service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
   
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/{Id}")
	public Customer getCustomer(@PathVariable("Id") Integer id)
	{    
		return customerService.findCustomer(id);
	}
	@GetMapping("")
	public List<Customer> getAllCustomer()
	{    
		return customerService.allCustomer();
	}
	
	@PostMapping("/")
	public Customer SaveStudent(@Valid @RequestBody Customer customer)
	{	System.out.println(customer);
		return customerService.saveCustomer(customer);
	}
	@PutMapping("/update/{id}")
	public Customer updateStudent(@Valid @RequestBody Customer customer,@PathVariable("id")Integer id )
	{
		
		return customerService.updateCustomer(customer,id);
	}
	
	 @DeleteMapping("/delete/{id}")
		public String delete(@PathVariable("id")Integer id) {
			
	    	return customerService.deleteCustomer(id);
		}
	
	
}
