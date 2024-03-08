package com.MBD.CabBooking.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MBD.CabBooking.Entity.Customer;
import com.MBD.CabBooking.Exceptionhandling.ResourceNotFoundExcpetion;
import com.MBD.CabBooking.Repo.CustomerRepo;

import jakarta.validation.Valid;

@Service
public class CustomerService {

	
	@Autowired
	 private CustomerRepo customerRepo;
	
	
	public Customer findCustomer(Integer id) {
		// TODO Auto-generated method stub
		
		 return customerRepo.findById(id).orElseThrow(()->new ResourceNotFoundExcpetion("Customer", "id", id));
		
//		return null;
	}

	public List<Customer> allCustomer() {
		// TODO Auto-generated method stub
		
		
		return customerRepo.findAll();
//		return null;
	}

	public Customer saveCustomer(@Valid Customer customer) {
		// TODO Auto-generated method stub
		
		return customerRepo.save(customer);
//		return null;
	}

	public Customer updateCustomer(@Valid Customer customer, Integer id) {
		// TODO Auto-generated method stub
		Customer customer2=customerRepo.findById(id).orElseThrow(()->new ResourceNotFoundExcpetion("Customer", "id", id));
		customer2.setEmail(customer.getEmail());
		customer2.setJourney_status(customer.getJourney_status());
		customer2.setPassword(customer.getPassword());
		customer2.setUsername(customer.getUsername());

		customerRepo.save(customer2);
		return customer2;
	}

	public String deleteCustomer(Integer id) {
		// TODO Auto-generated method stub
		Customer customer2=customerRepo.findById(id).orElseThrow(()->new ResourceNotFoundExcpetion("Customer", "id", id));
		customerRepo.delete(customer2);
		return "Customer Deleted Successfully";
	}

	

}
