package com.MBD.CabBooking.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.MBD.CabBooking.Entity.TripArchives;
import com.MBD.CabBooking.Entity.TripBooking;
import com.MBD.CabBooking.Repo.TripArchiveRepo;
import com.MBD.CabBooking.Repo.TripRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TripBookingCleanupService {
	
	    @Autowired
	   private TripRepo tripRepo;
	    @Autowired
	    private TripArchiveRepo triparchiverepo;
	
	 @Scheduled(fixedRate = 3000)
	 public void cleanupTripBooking() 
	 {
	     LocalDateTime thresholdTime = LocalDateTime.now().minusMinutes(5); 
	       List<TripBooking>outadedTripBookings=  tripRepo.outdatedTrips(thresholdTime);
	     
	       if(outadedTripBookings.size()>=1)
	       {
	    	   
	       List<TripArchives> archivedTransactions = new ArrayList<>();
			       for(TripBooking eleBooking:outadedTripBookings)
			       {
			    	   TripArchives tempArchives=new TripArchives();
			    	   
			    	   tempArchives.setCabType(eleBooking.getCabType());
			    	   tempArchives.setCustomerId(eleBooking.getCustomerId());
			    	   tempArchives.setDriver(eleBooking.getDriver());
			    	   tempArchives.setEntryTime(eleBooking.getEntryTime());
			    	   tempArchives.setFrom_location(eleBooking.getFrom_location());
			    	   tempArchives.setFromdate_time(eleBooking.getFromdate_time());
			    	   tempArchives.setPayment(eleBooking.getPayment());
			    	   tempArchives.setTotalamount(eleBooking.getTotalamount());
			    	   tempArchives.setTripBookingId(eleBooking.getTripBookingId());
			    	   tempArchives.setKm(eleBooking.getKm());
			    	   tempArchives.setTo_location(eleBooking.getTo_location());
			    	   tempArchives.setTodate_time(eleBooking.getTodate_time());
			    	   
			    	   
			    	    archivedTransactions.add(tempArchives);
			    	    
			    	    tripRepo.deleteAll(outadedTripBookings);
			    	    triparchiverepo.saveAll(archivedTransactions);
			       }
	       }
	     System.out.println("Trip Cleanup Completed!!!");
	     
	     
	     
	     
	 }

}
