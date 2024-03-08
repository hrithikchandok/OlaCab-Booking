package com.MBD.CabBooking.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MBD.CabBooking.Entity.Cab;
import com.MBD.CabBooking.Entity.Driver;

@Repository
public interface CabRepo  extends JpaRepository<Cab, Integer> {
   
	@Query("SELECT c.driver FROM Cab c WHERE c.cabType = :cabType")
    List<Driver> findDriversByCabType(String cabType);
}
