package com.MBD.CabBooking.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MBD.CabBooking.Entity.TripBooking;

public interface TripRepo extends JpaRepository<TripBooking , Integer> {
 
}
