package com.MBD.CabBooking.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MBD.CabBooking.Entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

}
