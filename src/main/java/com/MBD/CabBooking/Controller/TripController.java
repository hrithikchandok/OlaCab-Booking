package com.MBD.CabBooking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MBD.CabBooking.Entity.TripBooking;
import com.MBD.CabBooking.Service.TripService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/trip")
public class TripController {
	   @Autowired
	   private TripService tripService;
	   
	   @PostMapping("")
		public TripBooking Add( @Valid @RequestBody TripBooking trip)
		{
			return tripService.AddTrip(trip);
		}
	   

		@GetMapping("")
		public List<TripBooking> getAllCustomer()
		{    
			return tripService.alltrip() ;
		}
		
		@PutMapping("/{id}")
		public TripBooking updateStudent(@PathVariable("id")Integer id)
		{
			
			return tripService.updateTrip(id);
		}
		 @DeleteMapping("/{id}")
			public String delete(@PathVariable("id")Integer id) {
				
		    	return tripService.deletetrip(id);
			}
	   
	     
}
