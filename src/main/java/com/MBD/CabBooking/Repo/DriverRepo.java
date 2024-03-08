package com.MBD.CabBooking.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MBD.CabBooking.Entity.Driver;
@Repository
public interface DriverRepo extends JpaRepository<Driver,Integer> {

	@Query("SELECT d FROM Driver d WHERE d.rating >= 4.5 AND d.available = true")
	List<Driver> findByRatingGreaterThanAndAvailableIsTrue();
	@Query("SELECT d FROM Driver d WHERE d.available = true")
	List<Driver> findByAvailable();

}
