package com.MBD.CabBooking.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.print.event.PrintJobAttributeEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.MBD.CabBooking.Entity.Customer;
import com.MBD.CabBooking.Entity.Driver;
import com.MBD.CabBooking.Entity.TripBooking;
import com.MBD.CabBooking.Exceptionhandling.ResourceNotFoundExcpetion;
import com.MBD.CabBooking.Repo.CabRepo;
import com.MBD.CabBooking.Repo.CustomerRepo;
import com.MBD.CabBooking.Repo.DriverRepo;
import com.MBD.CabBooking.Repo.TripRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@Service
public class TripService {
	@Autowired
   private TripRepo tripRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private DriverRepo driverRepo;
	
	
	@Autowired
	private CabRepo cabRepo;
	

	public TripBooking AddTrip(@Valid TripBooking trip) {
		
		   
		   Customer cus=customerRepo.findById(trip.getCustomerId()).orElseThrow(()->new ResourceNotFoundExcpetion("Customer","id", trip.getCustomerId()));
//		   
//		   List<Driver> drivers= driverRepo.findByAvailable() ;
		   
		   // get all the drivers having cabtype same as the requested 
		   List<Driver>drivers=cabRepo.findDriversByCabType(trip.getCabType().toLowerCase());
		   
		   
		// Filter the list of drivers to get only the available ones
	        List<Driver> availableDrivers = drivers.stream()
	                                              .filter(Driver::isAvailable) // Assuming 'isAvailable' is a method in Driver entity
	                                              .collect(Collectors.toList());

		   
//		   Collections.sort(drivers, Comparator.comparing(obj->obj.getRatePerKm()).thenComparing(null));
		   Comparator<Driver> customComparator = Comparator.comparing(Driver::getRatePerKm)
                   .thenComparing(Driver::getRating, Comparator.reverseOrder());
		   
		   Collections.sort(availableDrivers, customComparator);

		   
		     if(availableDrivers.size()==0)
		     {
		    	  throw new ResourceNotFoundExcpetion("Sorry No driver Available just now...");
		     }
		     
		     //sorting the available driver based first based on the rateperKm and if the rates are same then compare of rating 
		     availableDrivers.forEach(obj->System.out.println(obj.getRatePerKm()+"and "+obj.getRating()));
		     Driver selected_Driver=availableDrivers.get(0);
		     
		     RestTemplate restTemplate = new RestTemplate();
		  // Define URL
		        String url = "https://distanceto.p.rapidapi.com/distance/route";
		        
		     // Define HTTP method
		        HttpMethod method = HttpMethod.POST; 
		        
		     // Set headers
		        HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_JSON);
		        headers.set("X-RapidAPI-Key", "3bc4b9c0ddmshab1f5eef080c818p1a59ffjsn79d2cb3a2dd7"); 
		        headers.set("X-RapidAPI-Host", "distanceto.p.rapidapi.com");
		        
		        String city1 = trip.getFrom_location();
		        String city2 = trip.getTo_location();
		        String requestBody = String.format("{\"route\": [{\"name\": \"%s\"}, {\"name\": \"%s\"}]}", city1, city2);
		        
		     // Create HttpEntity with headers and body
		        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
		        
		        // Execute the request
		        ResponseEntity<String> responseEntity = restTemplate.exchange(url, method, requestEntity, String.class);
		        
		     // Process response
		        HttpStatusCode statusCode = responseEntity.getStatusCode();
		        String responseBody = responseEntity.getBody();
		        
//		        System.out.println(responseBody);
		     // Parse JSON response using Jackson
		        ObjectMapper objectMapper = new ObjectMapper();
		        double distance=0.0;
		        double duration=0.0;
		        try {
		            JsonNode jsonNode = objectMapper.readTree(responseBody);

		            // Extract distance and duration from the "car" object within the "route" object
		            JsonNode stepsNode = jsonNode.path("steps").get(0); // Assuming there's only one step
		             distance = stepsNode.path("distance").path("car").path("distance").asDouble();
		             duration = stepsNode.path("distance").path("car").path("duration").asDouble();

		            System.out.println("Distance: " + distance);
		            System.out.println("Duration: " + duration);
		            
		        } catch (Exception e) {
		            // Handle JSON parsing exception
		            e.printStackTrace();
		        }
		        
		        trip.setTotalamount(selected_Driver.getRatePerKm()*(int)distance);	   
		        
		        trip.setKm((int)distance);
		        trip.setDriver(selected_Driver);
		        selected_Driver.setAvailable(false);
		        
		        
		        
		        driverRepo.save(selected_Driver);
		        		tripRepo.save(trip);
		        return trip;
		        
		       
            

		        
		     
	}


	public List<TripBooking> alltrip() {
		// TODO Auto-generated method stub
		
		return tripRepo.findAll();
//		return null;
	}


	public TripBooking updateTrip(Integer id) {
		// TODO Auto-generated method stub
		
		
		TripBooking trip1= tripRepo.findById(id).orElseThrow(()->new ResourceNotFoundExcpetion("Trip","Id",id));
		trip1.setPayment(true);
		tripRepo.save(trip1);
		return trip1;
//		return null;
	}


	public String deletetrip(Integer id) {
		// TODO Auto-generated method stub
		
		
		TripBooking trip= tripRepo.findById(id).orElseThrow(()->new ResourceNotFoundExcpetion("Trip","Id",id));
	     Driver driver= trip.getDriver();
	     driver.setAvailable(true);
	     driverRepo.save(driver);
	     tripRepo.delete(trip);
		return "Trip got Canceled , Driver get freed";
	}
}
