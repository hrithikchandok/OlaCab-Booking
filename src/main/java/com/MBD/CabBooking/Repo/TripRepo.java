package com.MBD.CabBooking.Repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.MBD.CabBooking.Entity.TripBooking;

public interface TripRepo extends JpaRepository<TripBooking , Integer> {

	@Query("Select T from TripBooking T where T.entryTime<:thresholdTime or T.entryTime is NULL")
	List<TripBooking> outdatedTrips(LocalDateTime thresholdTime);

//	@Modifying
//	@Query("DELETE FROM TripBooking T WHERE T.entryTime < :thresholdTime or T.entryTime IS NULL")
//	void deleteTrip(LocalDateTime thresholdTime);
	
	
 
}
